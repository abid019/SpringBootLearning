package com.abid123.module1introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Module1introductionApplication implements CommandLineRunner {
    @Autowired
    PaymentService payment1;
    @Autowired
    PaymentService payment2;
	public static void main(String[] args) {
		SpringApplication.run(Module1introductionApplication.class, args);
//        PaymentService payment1 = new PaymentService();
//        payment1.paymentMethod();
	}
    @Override
    public void run(String... args) throws Exception {
        System.out.println(payment1.hashCode());
        System.out.println(payment2.hashCode());
        payment1.paymentMethod();
    }
}