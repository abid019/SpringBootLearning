package com.abid123.module1introduction;

import com.abid123.module1introduction.impl.EmailNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
//    @Autowired
//    PaymentService payment1;
//    @Autowired
//    PaymentService payment2;
//    @Autowired                      // springboot way to DI (not preferred for production env)
//    private final NotificationService notificationService;
//    Module1introductionApplication( NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }

    @Autowired
    Map<String, NotificationService> notificationServicesMap = new HashMap<>();
//    @Autowired
//    private NotificationService notificationService;

    public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
//        PaymentService payment1 = new PaymentService();
//        payment1.paymentMethod();
	}



//    NotificationService notificationService = new EmailNotification();  // this is manual concrete implementation of creating object (tight coupling)
    @Override
    public void run(String... args) throws Exception {
//        System.out.println(payment1.hashCode());
//        System.out.println(payment2.hashCode());
//        payment1.paymentMethod();
//        notificationService.sendNotification("Hello");
            for(var notificationService:notificationServicesMap.entrySet()) {
                System.out.println(notificationService.getKey());
                notificationService.getValue().sendNotification("hello");
            }
    }
}