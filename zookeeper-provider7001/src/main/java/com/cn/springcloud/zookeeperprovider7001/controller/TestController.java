package com.cn.springcloud.zookeeperprovider7001.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-11 15:09
 **/
@RestController
public class TestController {

    @Value("${server.port}")
    private  String serverport;


    @GetMapping(value = "/provider/get/{id}")
    public String getProviderInfo(@PathVariable("id") Long id){


        return "Server.Port:"+ serverport+"  id:"+id+Thread.currentThread().getName();
    }
}
