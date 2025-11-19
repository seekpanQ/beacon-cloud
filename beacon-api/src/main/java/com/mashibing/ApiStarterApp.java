package com.mashibing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class ApiStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiStarterApp.class, args);
    }
}
