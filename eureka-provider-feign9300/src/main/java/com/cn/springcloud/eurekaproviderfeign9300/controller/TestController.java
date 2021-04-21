package com.cn.springcloud.eurekaproviderfeign9300.controller;

import com.cn.springcloud.eurekaproviderfeign9300.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-13 22:57
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
    @GetMapping("/ribbon/get_demo")
    public DemoDTO getDemo(DemoDTO demoDTO) {
        return demoDTO;
    }

    @PostMapping("/ribbon/post_demo")
    public DemoDTO postDemo(@RequestBody DemoDTO demoDTO) {
        return demoDTO;
    }


}