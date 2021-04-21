package com.cn.springcloud.eurekacenterconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class EurekaCenterConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaCenterConfigApplication.class, args);
    }

}
