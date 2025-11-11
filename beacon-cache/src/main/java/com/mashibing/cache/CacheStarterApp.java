package com.mashibing.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * cache module启动类
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CacheStarterApp {
    public static void main(String[] args) {
        SpringApplication.run(CacheStarterApp.class, args);
    }
}
