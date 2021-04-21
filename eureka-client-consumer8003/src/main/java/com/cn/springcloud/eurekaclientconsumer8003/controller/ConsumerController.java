package com.cn.springcloud.eurekaclientconsumer8003.controller;


import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private static final String PAYMENT_URL="http://EUREKA-CLINET-PROVIDER";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${server.port}")
    private  String serverport;



    @GetMapping(value = "/consumer/get/{id}")
    public String getProviderInfo(@PathVariable("id") Long id){

        return restTemplate.getForObject(PAYMENT_URL+"/provider/get/"+id,String.class);


    }


}
