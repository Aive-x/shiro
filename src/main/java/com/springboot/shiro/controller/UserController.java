package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.util.JwtUtil;
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
 * @Date 2021/3/11 2:42 下午
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    private ActionReturnUtil updatePassword(@RequestParam("oldPass") String oldPass,
                                            @RequestParam("newPass") String newPass,
                                            @RequestParam("checkPass") String checkPass,
                                            ServletRequest request) {
        userService.updatePassword(oldPass, newPass, checkPass, request);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    private ActionReturnUtil getUserInfo(ServletRequest request) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return ActionReturnUtil.returnSuccessWithData(
            userService.getUserInfo(jwtUtil.getUsername(httpRequest.getHeader("Authorization"))));
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    private ActionReturnUtil listUsers() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(userService.listUsers());
    }

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    private ActionReturnUtil addUser(User user) throws Exception {
        userService.addUser(user);
        return ActionReturnUtil.returnSuccess();
    }

}
