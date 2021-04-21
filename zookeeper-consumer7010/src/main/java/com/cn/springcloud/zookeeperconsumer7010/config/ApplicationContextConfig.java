package com.cn.springcloud.zookeeperconsumer7010.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-11 16:04
 **/
@Configuration
public class ApplicationContextConfig {

    @Bean
    @LoadBalanced//开启负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
