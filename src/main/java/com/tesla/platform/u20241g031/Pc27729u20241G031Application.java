package com.tesla.platform.u20241g031;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Main entry point for the Tesla Autonomous Fleet Management Platform.
 *
 * @author Piero Francisco Montes Chang
 */
@SpringBootApplication
@EnableJpaAuditing
public class Pc27729u20241G031Application {

    public static void main(String[] args) {
        SpringApplication.run(Pc27729u20241G031Application.class, args);
    }
}