package com.springboot.shiro.controller;

import com.springboot.shiro.common.ActionReturnUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2020/9/23 15:50
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @ResponseBody
    @RequestMapping( method = RequestMethod.GET)
    public ActionReturnUtil showUser() {
        System.out.println("123");
        return ActionReturnUtil.returnSuccessWithData("success");
    }

}
