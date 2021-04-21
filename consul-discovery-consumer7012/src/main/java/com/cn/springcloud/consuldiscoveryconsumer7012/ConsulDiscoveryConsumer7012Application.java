package com.cn.springcloud.consuldiscoveryconsumer7012;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulDiscoveryConsumer7012Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsulDiscoveryConsumer7012Application.class, args);
    }

}
