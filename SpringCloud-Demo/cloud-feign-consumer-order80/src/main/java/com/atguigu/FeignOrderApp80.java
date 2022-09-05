package com.atguigu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FeignOrderApp80 {

    public static void main(String[] args) {
        SpringApplication.run(FeignOrderApp80.class, args);
    }
}
