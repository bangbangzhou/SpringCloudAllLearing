package com.cn.springcloud.eurekaclientgateway8527.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-20 22:34
 **/
@Configuration
public class GatewayTestConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route("guoji_route",r-> r.path("/guoji")
                        .uri("http://news.baidu.com/guoji"))
                .build();

    }


}