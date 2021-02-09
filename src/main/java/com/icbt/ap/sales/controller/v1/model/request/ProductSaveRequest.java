package com.icbt.ap.sales.controller.v1.model.request;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.Validation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

/**
 * @author Tharindu Eranga
 * @date Tue 09 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class ProductSaveRequest {
    @NotBlank(message = Validation.NAME_REQUIRED)
    private String name;
}
