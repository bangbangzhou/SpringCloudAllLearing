package com.cn.springcloud.eurekahystrixprovider8210.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-15 23:59
 **/
@Service
public class ProviderService {

    @Value("${server.port}")
    private String server_port;

    public String providerInfo_ok(Integer id){
        return "线程池:" +Thread.currentThread().getName()+"providerInfo_ok, idP  "+id;
    }


    @HystrixCommand(fallbackMethod = "providerInfo_TimeOutHabdler",commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String providerInfo_TimeOut(Integer id){
        //int timeNumber=1;
        int timeNumber=3;
        try{
            TimeUnit.SECONDS.sleep(timeNumber);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  "线程池: "+Thread.currentThread().getName()+"providerInfo_TimeOut,id:"+id + "耗时(秒)" + timeNumber;
    }


    private String providerInfo_TimeOutHabdler(Integer id){
        return "线程池：" + Thread.currentThread().getName() + "8210系统繁忙或者运行报错,请稍后再试,id:" + id + "\t";
    }


    //====服务熔断

    /**
     * 在10秒窗口期中10次请求有6次是请求失败的,断路器将起作用
     *
     * @param id
     * @return
     */
    @HystrixCommand(
            fallbackMethod = "providerCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),// 时间窗口期/时间范文
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")// 失败率达到多少后跳闸
    }
    )
    public String providerCircuitBreaker( Integer id) {
        if (id < 0) {
            throw new RuntimeException("*****id不能是负数");
        }
        String serialNumber = UUID.randomUUID().toString();
        return Thread.currentThread().getName() + "\t" + "调用成功,流水号:" + serialNumber;
    }

    public String providerCircuitBreaker_fallback( Integer id) {
        return "id 不能负数,请稍后重试,o(╥﹏╥)o id:" + id;
    }
}
