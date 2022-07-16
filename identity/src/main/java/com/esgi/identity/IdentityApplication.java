package com.esgi.identity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("checkstyle:FinalClass")
@SpringBootApplication
public class IdentityApplication {

    public static void main(final String[] args) {
        SpringApplication.run(IdentityApplication.class, args);
    }

    private IdentityApplication() { }
}
