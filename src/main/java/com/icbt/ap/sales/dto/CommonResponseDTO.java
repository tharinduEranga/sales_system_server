package com.icbt.ap.sales.dto;

import lombok.*;

/**
 * @author Tharindu Eranga
 * @date Mon 08 Feb 2021
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class CommonResponseDTO {
    private String code;
    private String message;
}
