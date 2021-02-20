package com.icbt.ap.sales.controller.v1.model.request;

import com.icbt.ap.sales.controller.v1.util.ApiConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Tharindu Eranga
 * @date Sat 20 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class StockRequestDetailRequest {
    private String id;
    @NotBlank(message = ApiConstant.Validation.PRODUCT_ID_REQUIRED)
    private String productId;
    @NotNull
    @Min(1)
    private Integer qty;
}
