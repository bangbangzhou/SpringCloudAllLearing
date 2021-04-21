package com.cn.springcloud.consuldiscoveryprovider7002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulDiscoveryProvider7002Application {

    public static void main(String[] args) {
        SpringApplication.run(ConsulDiscoveryProvider7002Application.class, args);
    }

}
