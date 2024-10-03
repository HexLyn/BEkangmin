package com.be.recentView.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecentViewedItemDto {
    private int productId;
    private String productName;
    private String productType;
}
