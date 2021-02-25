package com.icbt.ap.sales.util.notification.factory;

import com.icbt.ap.sales.util.notification.NotificationSender;
import com.icbt.ap.sales.util.notification.impl.EmailNotification;
import com.icbt.ap.sales.util.notification.impl.SmsNotification;

/**
 * @author Tharindu Eranga
 * @date Thu 25 Feb 2021
 */
public class NotificationFactory {

    private static NotificationFactory notificationFactory;

    private NotificationFactory() {
    }

    public static NotificationFactory getInstance() {
        if (notificationFactory == null) {
            notificationFactory = new NotificationFactory();
        }
        return notificationFactory;
    }

    public NotificationSender getNotificationSender(NotificationType type) {
        switch (type) {
            case SMS:
                return SmsNotification.getInstance();
            case EMAIL:
                return EmailNotification.getInstance();
            default:
                throw new UnsupportedOperationException("Given type is not implemented yet");
        }
    }

    public enum NotificationType {
        SMS, EMAIL
    }
}
