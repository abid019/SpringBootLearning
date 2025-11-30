package com.abid123.module1introduction.impl;

import com.abid123.module1introduction.NotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
//@Primary
@Component
@Qualifier("sms")
//@ConditionalOnProperty(name = "notification.type", havingValue="sms")
public class SmsNotification implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending sms... " + message);
    }
}
