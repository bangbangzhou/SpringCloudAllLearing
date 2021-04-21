package com.cn.springcloud.eurekaclientconfig8700;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@EnableEurekaClient
@SpringBootApplication
public class EurekaClientConfig8700Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConfig8700Application.class, args);
    }

}
