package com.springboot.shiro.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.auth.JwtUtil;
import com.springboot.shiro.dao.bean.Response;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.LoginService;
import com.springboot.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xutianhong
 * @Date 2020/9/23 15:23
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public String doLogin(String username, String password) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        subject.login(usernamePasswordToken);

        User user = (User) subject.getPrincipal();
        String token = jwtUtil.generateToken(JSONObject.parseObject(JSONObject.toJSONString(user)));

        return token;
    }

}
