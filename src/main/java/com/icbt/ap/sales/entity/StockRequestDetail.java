package com.icbt.ap.sales.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequestDetail {
    private String id;
    private Integer qty;
    /*FKs*/
    private String stockRequestId;
    private String stockId;
}
