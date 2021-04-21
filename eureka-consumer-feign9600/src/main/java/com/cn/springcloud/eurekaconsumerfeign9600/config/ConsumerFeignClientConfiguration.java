package com.cn.springcloud.eurekaconsumerfeign9600.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 00:38
 **/

public class ConsumerFeignClientConfiguration {
    @Bean
    public Logger.Level defaultFeignClientLoggerLevel() {
        return Logger.Level.BASIC;
    }
}
