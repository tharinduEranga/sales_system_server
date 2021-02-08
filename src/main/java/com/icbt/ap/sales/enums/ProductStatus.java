package com.icbt.ap.sales.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Getter
@AllArgsConstructor
public enum ProductStatus {

    ACTIVE(1, "Active"), INACTIVE(0, "Inactive"), DELETED(-1, "Deleted");

    private final int id;
    private final String description;

    public static List<ProductStatus> getList() {
        return Arrays.asList(ProductStatus.values().clone());
    }

    public static ProductStatus getById(int id) {
        return getList().stream().filter(productStatus -> productStatus.id == id).findFirst().orElse(null);
    }
}
