package com.icbt.ap.sales.controller.v1.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Tue 09 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class ProductResponse {
    private String id;
    private String name;
    private String status;
    private Integer statusId;
    private String createdAt;
}
