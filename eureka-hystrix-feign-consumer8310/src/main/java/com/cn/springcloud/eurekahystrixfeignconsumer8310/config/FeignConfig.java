package com.cn.springcloud.eurekahystrixfeignconsumer8310.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-18 20:13
 **/
//@Configuration
public class FeignConfig {


    /**
     * 配置请求重试
     *
     */
    @Bean
    public Retryer feignRetryer() {
        return new Retryer.Default(200, SECONDS.toMillis(2), 10);
    }


    /**
     * 设置请求超时时间
     *默认
     * public Options() {
     * this(10 * 1000, 60 * 1000);
     * }
     *
     */
    @Bean
    Request.Options feignOptions() {
        return new Request.Options(60 * 1000, 60 * 1000);
    }



    /**
     * 打印请求日志
     * @return
     */
    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

}
