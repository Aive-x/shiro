package com.springboot.shiro.controller;

import com.springboot.shiro.service.NewService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author weiyifan
 * @Date 2021/3/22 11:00 中午
 */
@Controller
@Slf4j
@RequestMapping("/new")
public class NewController {

    @Autowired
    private NewService newService;
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ActionReturnUtil getNew() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(newService.getNew());
    }
}
