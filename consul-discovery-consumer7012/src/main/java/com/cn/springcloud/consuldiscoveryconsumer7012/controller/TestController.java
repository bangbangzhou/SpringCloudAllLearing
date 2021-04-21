package com.cn.springcloud.consuldiscoveryconsumer7012.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-11 20:10
 **/
@RestController
@RequestMapping("/consumer")
public class TestController {

//    private static final String INVOKE_URL = "http://consul-provider";
//
//    @Autowired
//    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;


    @RequestMapping("/payment/consul")
    public String get() {
//        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
//        return result;

        // <1> 获得服务 `demo-provider` 的一个实例
        ServiceInstance instance;
        if (true) {
            // 获取服务 `demo-provider` 对应的实例列表
            List<ServiceInstance> instances = discoveryClient.getInstances("consul-provider");
            // 选择第一个
            instance = instances.size() > 0 ? instances.get(0) : null;
        } else {
            instance = loadBalancerClient.choose("consul-provider");
        }
        // <2> 发起调用
        if (instance == null) {
            throw new IllegalStateException("获取不到实例");
        }
        String targetUrl = instance.getUri() + "/provider/consul" ;
        String response = restTemplate.getForObject(targetUrl, String.class);
        // 返回结果
        return  response;
    }
}
