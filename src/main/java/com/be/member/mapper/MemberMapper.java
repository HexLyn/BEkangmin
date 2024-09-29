package com.be.member.mapper;


import com.be.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    int insert(Member member);

    Member findOneByMemberID(String memberID);

    Member findOneByMemberEmail(String email);

    Member findOneByMemberNum(long memberNum);

}
