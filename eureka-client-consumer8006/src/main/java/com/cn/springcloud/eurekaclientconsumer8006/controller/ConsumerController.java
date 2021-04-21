package com.cn.springcloud.eurekaclientconsumer8006.controller;


import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@RestController
public class ConsumerController {


    private static final String PAYMENT_URL="http://EUREKA-CLINET-PROVIDER";
    private static final String SERVER_NAME="EUREKA-CLINET-PROVIDER";
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Value("${server.port}")
    private  String serverport;



    @GetMapping(value = "/consumer/get/{id}")
    public String getProviderInfo(@PathVariable("id") Long id){
        // <1> 获得服务 `EUREKA-CLINET-PROVIDER` 的一个实例
        ServiceInstance instance;
        // 获取服务 `EUREKA-CLINET-PROVIDER` 对应的实例列表
        List<ServiceInstance> instanceList = discoveryClient.getInstances(SERVER_NAME);


        if(CollUtil.isNotEmpty(instanceList)){
            // 选择第一个
            instance = instanceList.get(0);
        }else {
            instance= loadBalancerClient.choose(SERVER_NAME);

        }
        if(instance==null){
            throw  new IllegalStateException("获取不到实例");
        }
        String targetUrl = instance.getUri() + "/provider/get/" + id;
        String response = restTemplate.getForObject(targetUrl, String.class);
        return  response;
    }
}
