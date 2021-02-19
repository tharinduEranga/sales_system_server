package com.icbt.ap.sales.entity.query;

import com.icbt.ap.sales.entity.StockRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequestResult extends StockRequest {
    private String byBranchName;
    private String forBranchName;
    private String vehicleReg;
}
