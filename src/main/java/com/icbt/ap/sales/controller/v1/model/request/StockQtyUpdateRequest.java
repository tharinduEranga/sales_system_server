package com.icbt.ap.sales.controller.v1.model.request;

import com.icbt.ap.sales.controller.v1.util.ApiConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

/**
 * @author Tharindu Eranga
 * @date Thu 18 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockQtyUpdateRequest {
    @NotBlank(message = ApiConstant.Validation.ID_REQUIRED)
    private String id;
    @NotBlank(message = ApiConstant.Validation.QTY_REQUIRED)
    private Integer qty;
}
