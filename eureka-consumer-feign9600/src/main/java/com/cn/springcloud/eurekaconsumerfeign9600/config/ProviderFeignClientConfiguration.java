package com.cn.springcloud.eurekaconsumerfeign9600.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 00:39
 **/

public class ProviderFeignClientConfiguration {
    @Bean
    @Primary // 主 Bean
    public Logger.Level feignClientLoggerLevel() {
        return Logger.Level.FULL;
    }
}
