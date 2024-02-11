package com.ll.sbsk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SbskApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbskApplication.class, args);
    }

}
