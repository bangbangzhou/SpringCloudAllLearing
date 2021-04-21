package com.cn.springcloud.consuldiscoveryprovider7002.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-11 18:48
 **/
@RequestMapping("/provider")
@RestController
public class TestController {

    @Value("${server.port}")
    private  String server_port;

    @RequestMapping("/consul")
    public String paymentConsul() {
        return "provider consul :" + server_port + "\t" + UUID.randomUUID().toString();
    }
}
