package com.cn.springcloud.zookeeperprovider7001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperProvider7001Application {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperProvider7001Application.class, args);
    }

}

