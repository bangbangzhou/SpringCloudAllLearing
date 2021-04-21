package com.cn.springcloud.ribboneurekaprovider7300.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-11 23:06
 **/
@RequestMapping("/provider")
@Slf4j
@RestController
public class TestController {

    @Value("${server.port}")
    private  String server_port;

    @GetMapping(value = "/ribbon/get/{id}")
    public String get(@PathVariable("id") String id){
        return "port: "+server_port+" id:"+id+"  "+Thread.currentThread().getName();
    }

}
