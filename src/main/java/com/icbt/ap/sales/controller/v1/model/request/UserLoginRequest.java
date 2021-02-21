package com.icbt.ap.sales.controller.v1.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Sun 21 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class UserLoginRequest {
    private String username;
    private String password;
}
