package com.icbt.ap.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Wed 17 Feb 2021
 */
@Getter
@AllArgsConstructor
public enum BranchType {

    HEAD_OFFICE(1, "Head Office"), BRANCH(2, "Branch");

    private final Integer id;
    private final String description;

    public static List<BranchType> getList() {
        return Arrays.asList(BranchType.values().clone());
    }

    public static BranchType getById(Integer id) {
        return getList()
                .stream()
                .filter(branchType -> branchType.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}
