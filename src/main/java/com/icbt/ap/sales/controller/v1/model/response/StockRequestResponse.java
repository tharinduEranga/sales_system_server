package com.icbt.ap.sales.controller.v1.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Sat 20 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequestResponse {
    private String id;
    private String byBranchId;
    private String forBranchId;
    private String vehicleId;
    private String byBranchName;
    private String forBranchName;
    private String vehicleReg;
    private String status;
    private String createdAt;
    private String updatedAt;
    private List<StockRequestDetailResponse> stockRequestDetails;
}
