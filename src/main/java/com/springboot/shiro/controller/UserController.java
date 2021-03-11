package com.springboot.shiro.controller;

import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.ActionReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2021/3/11 2:42 下午
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    private ActionReturnUtil updatePassword(@RequestParam("username") String username,
                                            @RequestParam("password") String password){
        userService.updatePassword(username, password);
        return ActionReturnUtil.returnSuccess();
    }

}
