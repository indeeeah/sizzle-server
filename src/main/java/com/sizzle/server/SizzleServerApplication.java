package com.sizzle.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.sizzle.server.domains")
public class SizzleServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SizzleServerApplication.class, args);
    }

}
