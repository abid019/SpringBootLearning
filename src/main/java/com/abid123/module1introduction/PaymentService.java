package com.abid123.module1introduction;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//@Component
//@Service
//@Repository
//@Component
public class PaymentService {

    public void paymentMethod() {
        System.out.println("Paying...");
    }
    @PostConstruct
    public void afterInitilizatin(){
        System.out.println("After Initilizatin...");
    }
    @PreDestroy
    public void preDestroy(){
        System.out.println("PreDestroy...");
    }
}
