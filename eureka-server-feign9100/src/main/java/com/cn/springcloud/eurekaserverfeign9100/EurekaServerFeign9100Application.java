package com.cn.springcloud.eurekaserverfeign9100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServerFeign9100Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerFeign9100Application.class, args);
    }

}
