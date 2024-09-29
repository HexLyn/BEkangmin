package com.be.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.be.auth.type.Jwt;
import com.be.exception.CustomException;
import com.be.member.domain.Member;
import com.be.member.domain.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.be.common.code.ErrorCode.*;


@Component
@RequiredArgsConstructor
public class JwtProvider {

    @Value("${api.jwt.secret}")
    private String secret;

    @Value("${api.jwt.domain}")
    private String domain;

    private final CustomUserDetailsService customuserDetailsService;
    private final String tokenPrefix = "Bearer ";
    private final long accessTokenTime = 30L * 60 * 1000;
    private final long refreshTokenTime = 28L * 24 * 60 * 60 * 1000;

    /**
     * Guest Access JWT 생성
     */
    public HttpHeaders generateGuestJwt() {
        return jwtGenerator(0L, Collections.singletonList(Role.GUEST.name()), false);
    }

    /**
     * User Access & Refresh JWT 생성
     */
    public HttpHeaders generateUserJwt(Long userId, List<String> roles) {
        return jwtGenerator(userId, roles, true);
    }

    /**
     * Admin Access & Refresh JWT 생성
     */
    public HttpHeaders generateAdminJwt(Long userId, List<String> roles) {
        return jwtGenerator(userId, roles, true);
    }

    /**
     * Master Access JWT 생성
     */
    public HttpHeaders generateMasterJwt(Long userId, List<String> roles) {
        return jwtGenerator(userId, roles, false);
    }

    /**
     * 토큰 생성자
     */
    private HttpHeaders jwtGenerator(Long userId, List<String> roles, boolean isRefreshToken) {
        long time = System.currentTimeMillis();
        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));

        String accessJwt = JWT.create()
                .withSubject(userId.toString())
                .withIssuedAt(new Date(time))
                .withExpiresAt(new Date(time + accessTokenTime))
                .withIssuer(domain)
                .withClaim("roles", roles)
                .sign(algorithm);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, accessJwt);

        if (isRefreshToken) {
            String refreshJwt = JWT.create()
                    .withSubject(userId.toString())
                    .withIssuedAt(new Date(time))
                    .withExpiresAt(new Date(time + refreshTokenTime))
                    .withIssuer(domain)
                    .withClaim("roles", roles)
                    .sign(algorithm);

            httpHeaders.add("Refresh-Token", refreshJwt);
        }

        return httpHeaders;
    }

    /**
     * JWT Guest Access 인가
     */
    public void authorizeGuestAccessJwt(String token) { authorizeJwt(token, Role.GUEST, Jwt.ACCESS); }

    /**
     * JWT user Access 인가
     */
    public Member authorizeUserAccessJwt(String token) {
        return authorizeJwt(token, Role.ADMIN, Jwt.ACCESS);
    }

    /**
     * JWT user Refresh 인가
     */
    public Member authorizeUserRefreshJwt(String token) {
        return authorizeJwt(token, Role.MEMBER, Jwt.REFRESH);
    }

    /**
     * JWT Admin Access 인가
     */
    public Member authorizeAdminAccessJwt(String token) {
        return authorizeJwt(token, Role.ADMIN, Jwt.ACCESS);
    }

    /**
     * JWT Admin Refresh 인가
     */
//    public User authorizeAdminRefreshJwt(String token) {
//        return authorizeJwt(token, Role.ADMIN, Jwt.REFRESH);
//    }
//
//    /**
//     * JWT Master Access 인가
//     */
//    public User authorizeMasterAccessJwt(String token) {
//        return authorizeJwt(token, Role.MASTER, Jwt.ACCESS);
//    }

    /**
     * JWT 인증 |
     * 401(TOKEN_UNAUTHENTICATED)
     */
    public Member authenticateJwt(String token) {
        DecodedJWT decodedJWT = jwtDecoder(token);

        Long validTime = decodedJWT.getExpiresAt().getTime() - decodedJWT.getIssuedAt().getTime();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));
        long memberNum = Long.parseLong(decodedJWT.getSubject());
        boolean isExpired = decodedJWT.getExpiresAt()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime()
                .isBefore(LocalDateTime.now());

        // Active member validation
        Member member;
        if (memberNum == 0L && roles.contains(Role.GUEST.name()))
            member = null;
//        else if (memberNum > 0L && roles.contains(Role.ADMIN.name()))
//            member = customuserDetailsService.loadAdminByUserId(memberNum);
        else if (memberNum > 0L && roles.contains(Role.MEMBER.name()))
            member = customuserDetailsService.loadUserByMemberNum(memberNum);
        else
            throw new CustomException(TOKEN_UNAUTHENTICATED);

        // Token time validation
        if ((!validTime.equals(accessTokenTime) && !validTime.equals(refreshTokenTime)) || isExpired)
            throw new CustomException(TOKEN_UNAUTHENTICATED);

        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(memberNum, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        } catch (JWTVerificationException e) {
            throw new CustomException(e, TOKEN_UNAUTHENTICATED);
        }

        return member;
    }

    /**
     * JWT 인가 |
     * 401(TOKEN_UNAUTHENTICATED)
     * 403(TOKEN_UNAUTHORIZED)
     */
    private Member authorizeJwt(String token, Role role, Jwt jwt) {
        DecodedJWT decodedJWT = jwtDecoder(token);
        Member member = authenticateJwt(token);

        Set<String> roles = new HashSet<>(decodedJWT.getClaim("roles").asList(String.class));
        Long validTime = decodedJWT.getExpiresAt().getTime() - decodedJWT.getIssuedAt().getTime();

        // Token valid time validation
        if (jwt.name().equals(Jwt.ACCESS.name())) {
            if (!validTime.equals(accessTokenTime))
                throw new CustomException(TOKEN_UNAUTHORIZED);
        } else if (jwt.name().equals(Jwt.REFRESH.name())) {
            if (!validTime.equals(refreshTokenTime))
                throw new CustomException(TOKEN_UNAUTHORIZED);
        } else {
            throw new CustomException(SERVER_ERROR);
        }

        // Role validation
        if (role.name().equals(Role.GUEST.name())) {
            if (!roles.contains(role.name()))
                throw new CustomException(TOKEN_UNAUTHORIZED);
        } else {
            Set<String> roleSets = new HashSet<>(member.getRoles());
            if (!roles.contains(role.name()) || !roleSets.containsAll(roles))
                throw new CustomException(TOKEN_UNAUTHORIZED);
        }

        return member;
    }

    /**
     * JWT 디코더 |
     * 401(TOKEN_UNAUTHENTICATED)
     */
    private DecodedJWT jwtDecoder(String token) {
        if (token == null || token.trim().equals(""))
            throw new CustomException(TOKEN_UNAUTHENTICATED);
        token = token.trim().substring(tokenPrefix.length());

        Algorithm algorithm = Algorithm.HMAC256(secret.getBytes(StandardCharsets.UTF_8));
        JWTVerifier verifier = JWT.require(algorithm).build();

        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new CustomException(e, TOKEN_UNAUTHENTICATED);
        }
    }
}
