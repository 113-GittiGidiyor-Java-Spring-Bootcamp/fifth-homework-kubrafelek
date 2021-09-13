package com.kubrafelek.homework05;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2

public class Homework05Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework05Application.class, args);
    }

}
