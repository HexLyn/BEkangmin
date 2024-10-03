package com.be.recentView.controller;

import com.be.recentView.dto.RecentViewedItemDto;
import com.be.recentView.service.RecentViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/recentView")
@RequiredArgsConstructor
public class RecentViewController {
    private final RecentViewService recentViewService;

    @GetMapping
    public ResponseEntity<List<RecentViewedItemDto>> getRecentViewedItem(HttpServletRequest request) {
        return ResponseEntity.ok(recentViewService.getRecentViewItem(request));
    }

    @PostMapping /* -> 쿠키 생성(상품 조회시 ~ financeController로 이동) */
    public void addRecentViewedItem(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid RecentViewedItemDto dto) {
        recentViewService.addRecentViewedItem(request, response, dto);
    }
}
