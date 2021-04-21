package com.cn.springcloud.eurekaconsumerfeign9700.controller;

import com.cn.springcloud.eurekaconsumerfeign9700.dto.DemoDTO;
import com.cn.springcloud.eurekaconsumerfeign9700.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    @GetMapping("/ribbon/test_get_demo")
    public DemoDTO testGetDemo(@RequestParam("type") int type, DemoDTO demoDTO) {
        // 方式一
        if (type == 1) {
            return consumerService.getDemo(demoDTO);
        } else if (type == 2) {
            return consumerService.getDemo(demoDTO.getUsername(), demoDTO.getPassword());
        } else {
            // 方式三
            Map<String, Object> params = new HashMap<>();
            params.put("username", demoDTO.getUsername());
            params.put("password", demoDTO.getPassword());
            return consumerService.getDemo(params);
        }
    }

    @GetMapping("/ribbon/test_post_demo")
    public DemoDTO testPostDemo(DemoDTO demoDTO) {
        return consumerService.postDemo(demoDTO);
    }

}
