package com.sales.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableAsync
@EnableScheduling
public class MarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }

}
