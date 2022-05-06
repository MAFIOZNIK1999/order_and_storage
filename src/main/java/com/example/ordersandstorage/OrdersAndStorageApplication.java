package com.example.ordersandstorage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrdersAndStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersAndStorageApplication.class, args);
    }

}
