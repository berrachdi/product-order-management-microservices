package com.medtech.ordermicroservices;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class OrderMicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderMicroservicesApplication.class, args);
    }



}
