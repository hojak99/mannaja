package com.depromeet.mannaja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MannajaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MannajaApplication.class, args);
    }
}
