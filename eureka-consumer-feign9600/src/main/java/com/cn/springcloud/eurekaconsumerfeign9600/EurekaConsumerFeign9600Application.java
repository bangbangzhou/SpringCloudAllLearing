package com.cn.springcloud.eurekaconsumerfeign9600;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import com.cn.springcloud.eurekaconsumerfeign9600.config.ConsumerFeignClientConfiguration;

@EnableFeignClients(defaultConfiguration = ConsumerFeignClientConfiguration.class)
@SpringBootApplication
public class EurekaConsumerFeign9600Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeign9600Application.class, args);
    }

}
