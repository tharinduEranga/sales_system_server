package com.icbt.ap.sales.dto;

import lombok.*;

/**
 * @author Tharindu Eranga
 * @date Tue 09 Feb 2021
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class ContentResponseDTO<T> {
    private boolean success;
    private T data;
}
