package com.cn.springcloud.eurekaserverdemo8001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer
@SpringBootApplication
public class EurekaServerDemo8001Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerDemo8001Application.class, args);
    }

}
