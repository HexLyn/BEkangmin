package com.be.recentView.service;

import com.be.recentView.dto.RecentViewedItemDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface RecentViewService {
    void addRecentViewedItem(HttpServletRequest req, HttpServletResponse resp, RecentViewedItemDto dto);
    List<RecentViewedItemDto> getRecentViewItem(HttpServletRequest request);
    void resetCookies(HttpServletRequest req, HttpServletResponse resp);
}
