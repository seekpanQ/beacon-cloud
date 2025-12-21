package com.mashibing.smsgateway;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDynamicThreadPool
public class SmsGatewayStarterApp {

    public static void main(String[] args) {

        SpringApplication.run(SmsGatewayStarterApp.class, args);
    }

}
