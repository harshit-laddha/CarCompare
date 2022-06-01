package com.example.carcompare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.carcompare")
@EnableCaching
public class CarCompareApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarCompareApplication.class, args);
    }

}
