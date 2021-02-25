package com.springboot.shiro.controller;

import com.springboot.shiro.service.ClassesService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xutianhong
 * @Date 2021/2/25 12:22 下午
 */
@Controller
@Slf4j
@RequestMapping("/college")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    private ActionReturnUtil listClasses() {
        return ActionReturnUtil.returnSuccessWithData(classesService.listClasses());
    }

    @ResponseBody
    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    private ActionReturnUtil getClasses(@RequestParam("college") String college) {
        return ActionReturnUtil.returnSuccessWithData(classesService.getClasses(college));
    }

}
