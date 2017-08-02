package com.lxz.myBlog.controller;


import com.google.gson.Gson;
import com.lxz.myBlog.Mapper.userMapper;
import com.lxz.myBlog.Service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * Created by Administrator on 2017/7/20.
 */
@RestController
public class MainController {
    @Autowired
    private userService userService;

    @RequestMapping("/hello")
    public String index(){

        Gson gs = new Gson();
        return gs.toJson(userService.tryLogin("admin","123456"));
    }
}
