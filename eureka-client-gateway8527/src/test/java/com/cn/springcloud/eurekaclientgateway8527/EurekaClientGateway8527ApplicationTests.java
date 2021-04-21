package com.cn.springcloud.eurekaclientgateway8527;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZonedDateTime;

@SpringBootTest
class EurekaClientGateway8527ApplicationTests {

    @Test
    void contextLoads() {
        ZonedDateTime zbj =ZonedDateTime.now();
        System.out.println(zbj);
    }

}
