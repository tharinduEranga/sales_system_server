package com.icbt.ap.sales.util.notification.impl;

import com.icbt.ap.sales.util.notification.NotificationSender;
import com.icbt.ap.sales.util.notification.dto.NotificationDTO;

/**
 * @author Tharindu Eranga
 * @date Thu 25 Feb 2021
 */
public class EmailNotification implements NotificationSender {
    private static EmailNotification emailNotification;

    private EmailNotification() {
    }

    public static EmailNotification getInstance() {
        if (emailNotification == null) {
            emailNotification = new EmailNotification();
        }
        return emailNotification;
    }

    @Override
    public void sendNotification(NotificationDTO notificationDTO) {
        throw new UnsupportedOperationException("Email notification is not implemented yet!");
    }
}
