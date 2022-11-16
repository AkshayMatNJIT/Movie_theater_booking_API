package com.jpmc.theater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class Theater {

    public static void main(String[] args) {
        SpringApplication.run(Theater.class, args);
    }
}