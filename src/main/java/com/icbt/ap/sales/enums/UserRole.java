package com.icbt.ap.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Sun 21 Feb 2021
 */
@Getter
@AllArgsConstructor
public enum UserRole {

    HEAD_OFFICE_ADMIN(1, "Head Office Admin"), BRANCH_ADMIN(2, "Branch Admin");

    private final Integer id;
    private final String description;

    public static List<UserRole> getList() {
        return Arrays.asList(UserRole.values().clone());
    }

    public static UserRole getById(Integer id) {
        return getList()
                .stream()
                .filter(userRole -> userRole.id.equals(id))
                .findFirst()
                .orElse(null);
    }
}
