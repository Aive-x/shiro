package com.springboot.shiro.controller;

import com.springboot.shiro.service.ClassesService;
import com.springboot.shiro.service.TeacherClassesService;
import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.util.JwtUtil;
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
 * @Date 2021/4/20 4:59 下午
 */
@Controller
@RequestMapping("/teacher")
public class TeacherClassesController {

    @Autowired
    private TeacherClassesService teacherClassesService;
    @Autowired
    private ClassesService classesService;

    @ResponseBody
    @RequestMapping(value = "/classes", method = RequestMethod.POST)
    private ActionReturnUtil setTeacherClasses(HttpServletRequest request,
                                               @RequestParam("ids") String ids) throws Exception {
        teacherClassesService.setTeacherClasses(JwtUtil.getUsername(request), ids);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/classes/list", method = RequestMethod.GET)
    private ActionReturnUtil setTeacherClasses() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(classesService.listClasses());
    }

}
