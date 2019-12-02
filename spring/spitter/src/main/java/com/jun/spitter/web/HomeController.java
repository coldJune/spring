package com.jun.spitter.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller//声明控制器
@RequestMapping(value = {"/","/homepage"})
public class HomeController {
    @RequestMapping( method = GET)//处理对"/"的GET请求
    public String home(){
        return "home"; //视图名为home
    }
}
