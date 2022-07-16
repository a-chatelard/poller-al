package com.esgi.tags;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SuppressWarnings("checkstyle:FinalClass")
@SpringBootApplication
public class TagsApplication {
    public static void main(final String[] args) {
        SpringApplication.run(TagsApplication.class, args);
    }

    private TagsApplication() { }
}
