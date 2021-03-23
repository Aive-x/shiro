package com.springboot.shiro.controller;

import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author xutianhong
 * @Date 2021/3/9 7:10 下午
 */
@Slf4j
@Controller
public class TabsController {

    @RequestMapping(value = "/tabs", method = RequestMethod.GET)
    @ResponseBody
    public ActionReturnUtil getTabs(ServletRequest request) throws Exception{
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        String role = JwtUtil.getUserRole(httpRequest.getHeader("Authorization"));
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
