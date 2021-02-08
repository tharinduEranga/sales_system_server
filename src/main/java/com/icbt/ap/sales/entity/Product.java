package com.icbt.ap.sales.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@Data
@NoArgsConstructor
@SuperBuilder
public class Product {
    private String id;
    @EqualsAndHashCode.Exclude
    private String name;
    private LocalDateTime createdAt;
}
