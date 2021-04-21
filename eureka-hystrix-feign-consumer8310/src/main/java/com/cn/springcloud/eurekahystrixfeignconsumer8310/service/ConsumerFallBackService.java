package com.cn.springcloud.eurekahystrixfeignconsumer8310.service;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-18 21:44
 **/
@Component
public class ConsumerFallBackService implements ConsumerService{

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    @Override
    public String provider_ok(@PathVariable("id") Integer id){
        return "----ConsumerFallBackService fall back--provider_ok";
    }





    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @Override
    public String provider_TimeOut(@PathVariable("id") Integer id){

        return "----ConsumerFallBackService fall back--provider_TimeOut";
    }
}
