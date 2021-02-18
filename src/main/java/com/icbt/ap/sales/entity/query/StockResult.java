package com.icbt.ap.sales.entity.query;

import com.icbt.ap.sales.entity.Stock;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Thu 18 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockResult extends Stock {
    private String branchName;
    private String productName;
}
