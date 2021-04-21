package com.cn.springcloud.eurekahystrixfeignconsumer8310.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-16 01:05
 **/
@Component
@FeignClient(value = "EUREKA-HYSTRIX-PROVIDER",fallback = ConsumerFallBackService.class)
public interface  ConsumerService {
    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    @GetMapping("/provider/hystrix/ok/{id}")
    public String provider_ok(@PathVariable("id") Integer id);





    /**
     * 超时访问
     *
     * @param id
     * @return
     */
    @GetMapping("/provider/hystrix/timeout/{id}")
    public String provider_TimeOut(@PathVariable("id") Integer id);

}
