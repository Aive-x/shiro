package com.springboot.shiro.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.util.JwtUtil;
import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xutianhong
 * @Date 2020/9/23 15:23
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {


    @Override
    public String doLogin(String username, String password) throws Exception{

        Subject subject = SecurityUtils.getSubject();
        try{
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
        } catch (Exception exception){
            throw new MarsRuntimeException(ErrorCodeMessage.LOGIN_FAIL);
        }

        User user = (User) subject.getPrincipal();
        String token = JwtUtil.generateToken(JSONObject.parseObject(JSONObject.toJSONString(user)));
        log.info("登录成功，token:{}", token);
        return token;
    }

}
