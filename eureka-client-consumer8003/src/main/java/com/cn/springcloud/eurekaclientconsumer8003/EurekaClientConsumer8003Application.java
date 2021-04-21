package com.cn.springcloud.eurekaclientconsumer8003;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientConsumer8003Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumer8003Application.class, args);
    }

}
