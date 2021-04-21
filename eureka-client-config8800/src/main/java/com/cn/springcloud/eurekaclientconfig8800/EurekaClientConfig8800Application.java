package com.cn.springcloud.eurekaclientconfig8800;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class EurekaClientConfig8800Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConfig8800Application.class, args);
    }

}
