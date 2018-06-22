package com.yujie;

import com.yujie.config.WebSiteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    private WebSiteConfig webSiteConfig;
//    @RequestMapping("/hello")
//    @RequestMapping(value = "/hello")
    @RequestMapping(value = "/hello",method =RequestMethod.GET)
   // @GetMapping("/hello")
    public String hello(){
        return "hello spring boot!1";
    }
    @GetMapping("/config")
    public String getConfig(){
        return  webSiteConfig.toString();
    }
}
