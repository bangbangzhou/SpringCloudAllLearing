package com.cn.springcloud.eurekaconsumerfeign9600.controller;

import com.cn.springcloud.eurekaconsumerfeign9600.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 00:11
 **/
@RestController
@RequestMapping("/consumer")
public class TestController {

    @Autowired
    private ConsumerService consumerService;

    @GetMapping(value = "/ribbon/get/{id}")
    public String get(@PathVariable("id") String id){
        return   consumerService.get(id);
    }

}
