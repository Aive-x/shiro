package com.springboot.shiro.controller;

import com.springboot.shiro.service.SystemService;
import com.springboot.shiro.util.ActionReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @ResponseBody
    @RequestMapping(value = "/examine", method = RequestMethod.GET)
    private ActionReturnUtil getExamine() throws Exception{
        return ActionReturnUtil.returnSuccessWithData(systemService.getExamine());
    }

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    private ActionReturnUtil getUploadFile(@RequestParam("file") MultipartFile multipartFile,
                                           HttpServletRequest request) throws Exception{
        systemService.addUserByExcel(multipartFile, request);
        return ActionReturnUtil.returnSuccess();
    }


}
