package com.cn.springcloud.eurekahystrixfeignconsumer8310.controller;

import com.cn.springcloud.eurekahystrixfeignconsumer8310.service.ConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import org.checkerframework.framework.qual.Covariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-16 01:09
 **/
@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "gloabl_fallback")
public class ConsmerController {

    @Autowired
    private ConsumerService consumerService;

    /**
     * 正常访问
     * http://localhost/consumer/hystrix/ok/1
     *
     * @param id
     * @return
     */
   // @HystrixCommand
    @GetMapping("/hystrix/ok/{id}")
    public String provider_ok(@PathVariable("id") Integer id) {
        return consumerService.provider_ok(id);
    }

    /**
     * 超时访问指定的fallback
     *
     * @param id
     * @return
     */
    @GetMapping("/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "providerTimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
//    })
//    @HystrixCommand(fallbackMethod = "provider_TimeOutFallbackMethod", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")
//    })
    @HystrixCommand
    public String provider_TimeOut(@PathVariable("id") Integer id) {
        String result= consumerService.provider_TimeOut(id);
        return  result;
    }

    private String provider_TimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "消费者8310，对方支付系统繁忙，或自己运行出错请检查自己，请10秒后再试。";
    }

    @GetMapping("/hystrix/global/{id}")
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000")
//    })
    @HystrixCommand
    public String paymentInfo_TimeOut_Global(@PathVariable("id") Integer id) {
       // int age =10/0;
        return consumerService.provider_TimeOut(id);
    }


    public String gloabl_fallback(){
        return  "Gloabl 全局异常处理信息";
    }
}
