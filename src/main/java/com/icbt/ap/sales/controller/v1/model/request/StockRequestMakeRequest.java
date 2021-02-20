package com.icbt.ap.sales.controller.v1.model.request;

import com.icbt.ap.sales.controller.v1.util.ApiConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Sat 20 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequestMakeRequest {
    @NotBlank(message = ApiConstant.Validation.BY_BRANCH_REQUIRED)
    private String byBranchId;
    @NotBlank(message = ApiConstant.Validation.FOR_BRANCH_REQUIRED)
    private String forBranchId;
    private String vehicleId;
    @Valid
    @NotEmpty(message = ApiConstant.Validation.PRODUCT_DETAILS_REQUIRED)
    private List<StockRequestDetailRequest> stockRequestDetails;
}
