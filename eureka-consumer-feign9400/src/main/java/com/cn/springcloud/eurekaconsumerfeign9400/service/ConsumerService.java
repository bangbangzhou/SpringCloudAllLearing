package com.cn.springcloud.eurekaconsumerfeign9400.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "EUREKA-PROVIDER-FEIGN")
public interface ConsumerService {
    @GetMapping(value = "/provider/ribbon/get/{id}")
    public String get(@PathVariable("id") String id);
}
