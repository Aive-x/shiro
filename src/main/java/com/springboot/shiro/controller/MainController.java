package com.springboot.shiro.controller;

import com.springboot.shiro.service.bean.EpidemicInformation;
import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.service.EpidemicService;
import com.springboot.shiro.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2021/1/16 3:19 下午
 */
@Controller
@RequestMapping("")
public class MainController {

    @Autowired
    private EpidemicService epidemicService;

    @ResponseBody
    @RequestMapping(value = "/epidemic", method = RequestMethod.GET)
    public ActionReturnUtil getEpidemicInformation() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(epidemicService.getEpidemicInformation());
    }

}
