package com.icbt.ap.sales.controller.v1.model.request;

import com.icbt.ap.sales.controller.v1.util.ApiConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

/**
 * @author Tharindu Eranga
 * @date Sun 21 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class UserLoginRequest {
    @NotBlank(message = ApiConstant.Validation.USERNAME_REQUIRED)
    private String username;
    @NotBlank(message = ApiConstant.Validation.PASSWORD_REQUIRED)
    private String password;
}
