package com.cn.springcloud.eurekaconsumerfeign9700.service;

import com.cn.springcloud.eurekaconsumerfeign9700.dto.DemoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import com.cn.springcloud.eurekaconsumerfeign9700.config.ProviderFeignClientConfiguration;

import java.util.Map;

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

    @GetMapping("/provider/ribbon/get_demo") // GET 方式一，最推荐
    DemoDTO getDemo(@SpringQueryMap DemoDTO demoDTO);

    @GetMapping("/provider/ribbon/get_demo") // GET 方式二，相对推荐
    DemoDTO getDemo(@RequestParam("username") String username, @RequestParam("password") String password);

    @GetMapping("/provider/ribbon/get_demo") // GET 方式三，不推荐
    DemoDTO getDemo(@RequestParam Map<String, Object> params);

    @PostMapping("/provider/ribbon/post_demo") // POST 方式
    DemoDTO postDemo(@RequestBody DemoDTO demoDTO);
}
