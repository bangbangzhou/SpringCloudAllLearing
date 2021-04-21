package com.cn.springcloud.ribboneurekaconsumer7600.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-12 00:17
 **/
@RequestMapping("/consumer")
@RestController
public class TestController {


    @Autowired
    private RestTemplate restTemplate;

    private static final String INVOKE_URL = "http://ribbon-eureka-provider";

    @GetMapping(value = "/ribbon/get/{id}")
    public String get(@PathVariable("id")String id) {
        // 直接使用 RestTemplate 调用服务 `demo-provider`
        String targetUrl =INVOKE_URL+ "/provider/ribbon/get/+"+id;
        String response = restTemplate.getForObject(targetUrl, String.class);
        // 返回结果
        return  response;
    }
}
