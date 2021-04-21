package com.cn.springcloud.eurekaconsumerfeign9600.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.cn.springcloud.eurekaconsumerfeign9600.config.ProviderFeignClientConfiguration;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 00:07
 **/
@Component
@FeignClient(value = "EUREKA-PROVIDER-FEIGN",configuration = ProviderFeignClientConfiguration.class)
public interface ConsumerService {
    @GetMapping(value = "/provider/ribbon/get/{id}")
    public String get(@PathVariable("id") String id);
}
