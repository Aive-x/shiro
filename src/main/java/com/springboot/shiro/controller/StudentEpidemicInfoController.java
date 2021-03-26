package com.springboot.shiro.controller;

import com.springboot.shiro.service.StudentEpidemicInfoService;
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
 * @Date 2021/2/25 11:15 上午
 */
@Controller
@Slf4j
@RequestMapping("/student")
public class StudentEpidemicInfoController {

    @Autowired
    private StudentEpidemicInfoService studentEpidemicInfoService;

    @ResponseBody
    @RequestMapping(value = "/epidemic/page", method = RequestMethod.GET)
    private ActionReturnUtil getPage() throws Exception {
        return ActionReturnUtil
            .returnSuccessWithData(studentEpidemicInfoService.getStudentEpidemicInformation(false).size() / 15 + 1);
    }

    @ResponseBody
    @RequestMapping(value = "/epidemic", method = RequestMethod.GET)
    private ActionReturnUtil getStudentEpidemicInformation(@RequestParam("page") String page) throws Exception {
        return ActionReturnUtil
            .returnSuccessWithData(studentEpidemicInfoService.getStudentEpidemicInformationByPage(page));
    }

}
