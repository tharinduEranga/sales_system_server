package com.icbt.ap.sales.controller.v1.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.Validation;

/**
 * @author Tharindu Eranga
 * @date Tue 09 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class ProductUpdateRequest {
    @NotBlank(message = Validation.ID_REQUIRED)
    private String id;
    @NotBlank(message = Validation.NAME_REQUIRED)
    private String name;
    private Integer statusId;
}
