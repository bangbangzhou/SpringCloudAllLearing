package com.cn.springcloud.eurekaclientprovider8002.service;


import org.springframework.web.bind.annotation.PathVariable;

public interface IProviderService {



    public String getProviderInfo( Long id);

    public String getProviderlb( Long id);
}
