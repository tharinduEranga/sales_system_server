package com.icbt.ap.sales.dto;

import lombok.*;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class CommonResponseDTO {
    private boolean success;
    private String code;
    private String message;
}
