package com.cn.springcloud.ribboneurekaserver7200;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RibbonEurekaServer7200Application {

    public static void main(String[] args) {
        SpringApplication.run(RibbonEurekaServer7200Application.class, args);
    }

}
