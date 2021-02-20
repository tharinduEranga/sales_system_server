package com.icbt.ap.sales.controller.v1.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Sat 20 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequestDetailResponse {
    private String id;
    private String productId;
    private String productName;
    private Integer qty;
}
