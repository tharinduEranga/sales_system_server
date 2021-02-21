package com.icbt.ap.sales.entity;

import com.icbt.ap.sales.enums.UserRole;
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
public class User {
    private String id;
    private String username;
    private String password;
    private UserRole userRole;
    /*FKs*/
    private String branchId;
}
