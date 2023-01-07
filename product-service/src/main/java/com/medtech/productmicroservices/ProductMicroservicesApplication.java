package com.medtech.productmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ProductMicroservicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMicroservicesApplication.class, args);
    }

}
