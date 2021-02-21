package com.icbt.ap.sales.controller.v1.model.response;

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
public class UserLoginResponse {
    private String id;
    private String username;
    private String userRole;
    private String branchId;
}
