package com.pieces.spaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class SpaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpaServerApplication.class, args);
    }
}
