package com.education;

import config.RabbitConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(RabbitConfig.class)
public class EdoSecurityApplication {
    public static void main(String[] args) {
        SpringApplication.run(EdoSecurityApplication.class, args);
    }
}
