package com.sun.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 孙显圣
 * @version 1.0
 */
@Controller
public class IndexController {
    //编写方法，请求转发到登录页面
    @GetMapping(value = {"/", "/login"}) //这里设置了两个可以请求到这个Controller的网址
    public String login() {
        //这里由于配置了thyemleaf的启动器，所以不需要配置视图解析器，直接返回就会根据设置的前缀和后缀进行拼接找到指定的页面
        return "adminLogin";
    }

}
