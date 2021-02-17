package com.icbt.ap.sales.controller.v1.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

import static com.icbt.ap.sales.controller.v1.util.ApiConstant.Validation;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class BranchUpdateRequest {
    @NotBlank(message = Validation.ID_REQUIRED)
    private String id;
    @NotBlank(message = Validation.NAME_REQUIRED)
    private String name;
    @NotBlank(message = Validation.ADDRESS_REQUIRED)
    private String address;
    @NotBlank(message = Validation.TEL_REQUIRED)
    private String tel;
    private Integer statusId;
}
