package com.icbt.ap.sales.entity;

import com.icbt.ap.sales.enums.StockRequestStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequest {
    private String id;
    private StockRequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    /*FKs*/
    private String byBranchId;
    private String forBranchId;
    private String vehicleId;
}
