package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.service.CourseService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2021/3/5 5:10 下午
 */
@Controller
@Slf4j
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @ResponseBody
    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public ActionReturnUtil bindJwc(JwcAccount jwcAccount) throws Exception {
        courseService.jwcBind(jwcAccount);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ActionReturnUtil loginJwc(JwcAccount jwcAccount) throws Exception {
        courseService.doJwcLogin(jwcAccount);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ActionReturnUtil listCourses(JwcAccount jwcAccount) throws Exception {
        return ActionReturnUtil.returnSuccessWithData(courseService.listCourse(jwcAccount));
    }


}
