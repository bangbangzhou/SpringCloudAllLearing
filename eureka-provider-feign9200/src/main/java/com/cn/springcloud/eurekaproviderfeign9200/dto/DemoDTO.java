package com.cn.springcloud.eurekaproviderfeign9200.dto;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 01:24
 **/
public class DemoDTO {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public DemoDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public DemoDTO setPassword(String password) {
        this.password = password;
        return this;
    }

}