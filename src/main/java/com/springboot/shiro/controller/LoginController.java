package com.springboot.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.auth.JwtUtil;
import com.springboot.shiro.service.LoginService;
import com.springboot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author xutianhong
 * @Date 2020/9/23 15:21
 */
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpServletResponse response) {
        try {
            String token = loginService.doLogin(username, password);
            response.setHeader("Authorization", token);
            return "登陆成功";
        }catch (AuthenticationException e){
            log.error("User {} login fail, Reason:{}", username, e.getMessage());
            return e.getMessage();
        }catch (Exception e){
            return "Internal Server Error";
        }
    }
}
