package com.be.finance.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaginationService {

    public <T> Map<String, Object> paginate(List<T> list, int page, int pageSize) {
        int totalCount = list.size();
        int totalPages = (int) Math.ceil((double) totalCount / pageSize);

        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalCount);

        List<T> paginatedList = list.subList(startIndex, endIndex);

        Map<String, Object> result = new HashMap<>();
        result.put("items", paginatedList);
        result.put("currentPage", page);
        result.put("totalPages", totalPages);
        result.put("totalItems", totalCount);

        return result;
    }
}
