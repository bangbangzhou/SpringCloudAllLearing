package com.cn.springcloud.zookeeperconsumer7010.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-11 15:59
 **/
@RestController
@RequestMapping("/comsuner")
public class TestController {
    private static final String INVOKE_URL = "http://zookeeper-provider";

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/provider/zk/{id}")
    public String get(@PathVariable("id") String id) {

        String result = restTemplate.getForObject(INVOKE_URL + "/provider/get/"+id, String.class);
        return result;
    }
}
