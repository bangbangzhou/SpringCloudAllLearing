package com.cn.springcloud.eurekahystrixprovider8210.controller;

import com.cn.springcloud.eurekahystrixprovider8210.service.ProviderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-16 00:07
 **/
@RequestMapping("/provider")
@RestController
@Slf4j
public class ProviderController {

    @Autowired
    private ProviderService providerService;
    @GetMapping("/hystrix/ok/{id}")
    public String provider_ok(@PathVariable("id") Integer id){
        String info_ok = providerService.providerInfo_ok(id);
        log.info("------result:"+info_ok);
        return info_ok;
    }


    @GetMapping("/hystrix/timeout/{id}")
    public String provider_TimeOut(@PathVariable("id") Integer id){
        String info_timeout = providerService.providerInfo_TimeOut(id);
        log.info("------result:"+info_timeout);
        return info_timeout;

    }


    @GetMapping("/circuit/{id}")
    @HystrixCommand
    public String providerCircuitBreaker(@PathVariable("id") Integer id) {
        String result = providerService.providerCircuitBreaker(id);
        log.info("***result:" + result);
        return result;
    }

}
