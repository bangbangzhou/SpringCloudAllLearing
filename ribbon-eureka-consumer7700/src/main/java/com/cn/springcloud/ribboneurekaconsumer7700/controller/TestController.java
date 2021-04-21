package com.cn.springcloud.ribboneurekaconsumer7700.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-12 00:42
 **/
@RequestMapping("/consumer")
@RestController
public class TestController {


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;
    private static final String INVOKE_URL = "http://RIBBON-EUREKA-PROVIDER";
    private static final String INVOKE_NAME = "RIBBON-EUREKA-PROVIDER";
    @GetMapping(value = "/ribbon/get/{id}")
    public String get(@PathVariable("id")String id) {
        // 直接使用 RestTemplate 调用服务 `RIBBON-EUREKA-PROVIDER`
        // 获得服务 `RIBBON-EUREKA-PROVIDER` 的一个实例
        ServiceInstance instance = loadBalancerClient.choose(INVOKE_NAME);
        String targetUrl =instance.getUri()+ "/provider/ribbon/get/+"+id;
        String response = restTemplate.getForObject(targetUrl, String.class);
        // 返回结果
        return  response;
    }
}