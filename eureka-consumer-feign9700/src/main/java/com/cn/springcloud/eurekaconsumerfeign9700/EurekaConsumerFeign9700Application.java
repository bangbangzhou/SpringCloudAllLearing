package com.cn.springcloud.eurekaconsumerfeign9700;

import com.cn.springcloud.eurekaconsumerfeign9700.config.ConsumerFeignClientConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(defaultConfiguration = ConsumerFeignClientConfiguration.class)
@SpringBootApplication
public class EurekaConsumerFeign9700Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerFeign9700Application.class, args);
    }

}
