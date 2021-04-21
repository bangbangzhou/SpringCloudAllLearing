package com.cn.springcloud.eurekaserverdemo8004;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerDemo8004Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerDemo8004Application.class, args);
    }

}
