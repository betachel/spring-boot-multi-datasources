package com.beta.study;

import com.beta.study.mapper.jdbc.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2017/03/24
 */
@Controller
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world ! This is a simple web server power by spring-boot..............";
    }

    @RequestMapping("/error")
    @ResponseBody
    public String error() {
        return "Sorry! The server is sicking...............";
    }

    @RequestMapping("/db")
    @ResponseBody
    public String db() {
        System.out.println(userMapper.getAllUsers().get(0).getUserName());
        return "connettion to db...............";
    }


    @RequestMapping("/db2")
    @ResponseBody
    public String db2() {
        System.out.println(userMapper.getAllUsers().get(0).getUserName());
        return "connettion to db2...............";
    }
}
