package com.cn.springcloud.eurekaclientprovider8005.service.Impl;


import com.cn.springcloud.eurekaclientprovider8005.service.IProviderService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

/**
 * @author zbb
 */
@Service
public class ProviderServiceImpl implements IProviderService {

    @Override
    public String getProviderInfo(Long id) {
        return  "provider: "+Thread.currentThread().getName()+"  ID: "+id;

    }
    @Override
    public String getProviderlb( Long id){
        return  "lb: "+id;
    }
}
