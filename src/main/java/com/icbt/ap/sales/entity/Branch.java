package com.icbt.ap.sales.entity;

import com.icbt.ap.sales.enums.BranchStatus;
import com.icbt.ap.sales.enums.BranchType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class Branch {
    private String id;
    private String name;
    private String address;
    private String tel;
    private BranchType type;
    private BranchStatus status;
    private LocalDateTime createdAt;
}
