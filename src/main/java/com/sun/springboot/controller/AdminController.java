package com.sun.springboot.controller;

import com.sun.springboot.bean.Admin;
import com.sun.springboot.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * @author 孙显圣
 * @version 1.0
 */
@Controller
public class AdminController {
    //响应用户登录请求
    @PostMapping("/login")
    /**
     * Admin：自定义参数，会将请求的数据经过转换器之后封装到这个对象
     * HttpSession：获取session，当用户登录之后给session中设置一个值，进行权限验证
     * Model：放入到Model中的数据会存放到request域中
     */
    public String login(Admin admin, HttpSession session, Model model) {
        //验证是否合法
        if (StringUtils.hasText(admin.getName()) && "666".equals(admin.getPassword())) {
            //如果合法则将登录用户保存到session
            session.setAttribute("LoninAdmin", admin);

            //如果合法，重定向到manage.html
            return "redirect:/manage.html"; //注意这里的重定向是找到一个叫/manage.html的Controller
        } else {
            model.addAttribute("msg", "用户名或密码错误，请重新输入！");
            //如果不合法则重新登录
            return "adminLogin";
        }

    }

    //请求转发到manage.html
    @GetMapping("/manage.html")
    public String manage(Model model, @SessionAttribute(value = "LoninAdmin", required = false) Admin admin) {
        //验证用户是否登录
        if (admin != null) {
            //在这里模拟数据
            ArrayList<User> users = new ArrayList<>();
            users.add(new User(1, "关羽~", "666666", 20, "gy@sohu.com"));
            users.add(new User(2, "张飞", "666666", 30, "zf@sohu.com"));
            users.add(new User(3, "赵云", "666666", 22, "zy@sohu.com"));
            users.add(new User(4, "马超", "666666", 28, "mc@sohu.com"));
            users.add(new User(5, "黄忠", "666666", 50, "hz@sohu.com"));
            //将数据放到request域中
            model.addAttribute("users", users);
            return "manage";
        }
        else {
            model.addAttribute("msg", "你没有登录，请登录后访问！");
            //如果没有登录就返回登录页面
            return "adminLogin";
        }


    }

}
