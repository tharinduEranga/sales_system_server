package com.icbt.ap.sales.controller.v1.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class BranchResponse {
    private String id;
    private String name;
    private String address;
    private String tel;
    private String type;
    private Integer typeId;
    private String status;
    private Integer statusId;
    private String createdAt;
}
