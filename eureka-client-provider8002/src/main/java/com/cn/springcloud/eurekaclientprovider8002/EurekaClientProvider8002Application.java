package com.cn.springcloud.eurekaclientprovider8002;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
public class EurekaClientProvider8002Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProvider8002Application.class, args);
    }

}
