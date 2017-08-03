package com.lxz.myBlog.controller;

import com.google.gson.Gson;
import com.lxz.myBlog.Service.userService;
import com.lxz.myBlog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/3.
 */
@Controller
public class LoginController {
    @Autowired
    private userService userService;



    @RequestMapping(value = {"/","/index.html"})
    public ModelAndView LoginPage(){

        Gson gs = new Gson();
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/loginCheck.html")
    public ModelAndView loginCheck(HttpServletRequest request,LoginCommand loginCommand){
        boolean isValidUser =  userService.hasMatchUser(loginCommand.getUserName(),
                loginCommand.getPassword());
        if (!isValidUser) {
            return new ModelAndView("login", "error", "用户名或密码错误。");
        } else {
            User user = userService.findUserByUserName(loginCommand
                    .getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.loginSuccess(user);
            request.getSession().setAttribute("user", user);
            return new ModelAndView("main");
        }
    }

}
