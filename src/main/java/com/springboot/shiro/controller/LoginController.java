package com.springboot.shiro.controller;

import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.service.LoginService;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ActionReturnUtil login(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  HttpServletResponse response) throws Exception{
        String token = loginService.doLogin(username, password);
        response.setHeader("Authorization", token);
        return ActionReturnUtil.returnSuccessWithData(token);
    }
}
