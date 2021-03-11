package com.springboot.shiro.controller;

import com.springboot.shiro.service.SystemService;
import com.springboot.shiro.util.ActionReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.swing.*;

/**
 * @author xutianhong
 * @Date 2021/3/11 3:36 下午
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @ResponseBody
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    private ActionReturnUtil getLog() throws Exception{
        return ActionReturnUtil.returnSuccessWithData(systemService.getLog());
    }

}
