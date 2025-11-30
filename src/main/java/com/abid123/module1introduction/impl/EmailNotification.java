package com.abid123.module1introduction.impl;

import com.abid123.module1introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Email")
//@ConditionalOnProperty(name = "notification.type", havingValue="email")
public class EmailNotification implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("sending email..." + message);
    }
}
