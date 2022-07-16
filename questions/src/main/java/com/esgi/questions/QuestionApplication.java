package com.esgi.questions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("checkstyle:FinalClass")
@SpringBootApplication
public class QuestionApplication {

    public static void main(final String[] args) {
        SpringApplication.run(QuestionApplication.class, args);
    }

    private QuestionApplication() { }
}
