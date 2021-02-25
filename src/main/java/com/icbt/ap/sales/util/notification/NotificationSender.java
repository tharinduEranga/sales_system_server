package com.icbt.ap.sales.util.notification;

import com.icbt.ap.sales.util.notification.dto.NotificationDTO;

/**
 * @author Tharindu Eranga
 * @date Thu 25 Feb 2021
 */
public interface NotificationSender {
    void sendNotification(NotificationDTO notificationDTO);
}
