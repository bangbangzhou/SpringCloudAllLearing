package com.cn.springcloud.eurekaconsumerfeign9700.dto;

/**
 * @program: SpringCloudAllLearing
 * @description:
 * @author: zbb
 * @create: 2021-04-14 01:30
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