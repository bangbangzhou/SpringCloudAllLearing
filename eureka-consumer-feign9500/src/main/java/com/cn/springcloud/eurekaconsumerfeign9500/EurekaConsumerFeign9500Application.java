package com.cn.springcloud.eurekaconsumerfeign9500;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class EurekaConsumerFeign9500Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeign9500Application.class, args);
    }

}
