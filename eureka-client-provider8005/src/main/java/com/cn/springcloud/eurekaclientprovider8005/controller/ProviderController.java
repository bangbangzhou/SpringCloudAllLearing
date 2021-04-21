package com.cn.springcloud.eurekaclientprovider8005.controller;


import com.cn.springcloud.eurekaclientprovider8005.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    @Value("${server.port}")
    private  String serverport;



    @GetMapping(value = "/provider/get/{id}")
    public String getProviderInfo(@PathVariable("id") Long id){

        String info = providerService.getProviderInfo(id);

        return "Server.Port:"+ serverport+"  "+info;
    }

    @GetMapping(value = "/provider/lb/{id}")
    public String getProviderlb(@PathVariable("id") Long id){

        String info = providerService.getProviderlb(id);

        return "Server.Port:"+ serverport+"  "+info;
    }

}
