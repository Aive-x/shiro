package com.springboot.shiro.controller;

import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2021/3/9 7:10 下午
 */
@Slf4j
@Controller
public class TabsController {

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/tabs", method = RequestMethod.POST)
    @ResponseBody
    public ActionReturnUtil getTabs(@RequestParam("token") String token) throws Exception{
        String role = jwtUtil.getUserRole(token);
        String tabsNumber = "";
        switch (role){
            case "student":
                tabsNumber = "124";
                break;
            case "teacher":
                tabsNumber = "1234";
                break;
            case "admin":
                tabsNumber = "12345";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + role);
        }
        return ActionReturnUtil.returnSuccessWithData(tabsNumber);
    }


}
