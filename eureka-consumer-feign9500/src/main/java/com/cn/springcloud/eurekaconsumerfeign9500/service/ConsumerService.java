package com.cn.springcloud.eurekaconsumerfeign9500.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 00:07
 **/
@Component
@FeignClient(value = "EUREKA-PROVIDER-FEIGN")
public interface ConsumerService {
    @GetMapping(value = "/provider/ribbon/get/{id}")
    public String get(@PathVariable("id") String id);
}
