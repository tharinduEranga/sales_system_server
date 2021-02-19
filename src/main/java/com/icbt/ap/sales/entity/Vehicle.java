package com.icbt.ap.sales.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Tharindu Eranga
 * @date Fri 19 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class Vehicle {
    private String id;
    private String regNo;
    private String driverId;
    private LocalDateTime createdAt;
    /*FKs*/
    private String branchId;
}
