package com.icbt.ap.sales.util.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * @author Tharindu Eranga
 * @date Thu 25 Feb 2021
 */
@AllArgsConstructor
@Data
@SuperBuilder
public class NotificationDTO {
    private String message;
    private String topic;
    private String destination;
}
