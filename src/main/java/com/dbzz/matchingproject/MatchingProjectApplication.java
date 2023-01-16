package com.dbzz.matchingproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MatchingProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MatchingProjectApplication.class, args);
    }

}
