package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.ExamineService;
import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:21 下午
 */
@Controller
@Slf4j
@RequestMapping("/examine")
public class ExamineController {

    @Autowired
    private ExamineService examineService;

    @ResponseBody
    @RequestMapping(value = "/publish", method = RequestMethod.POST)
    public ActionReturnUtil publishStudentEpidemic(@RequestParam("ids") String ids,
                                                   @RequestParam("tags") String tags,
                                                   ServletRequest request) throws Exception {
        examineService.publishStudentEpidemic(ids, tags, JwtUtil.getUsername(request));
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public ActionReturnUtil publishCustomContent(StudentEpidemicInformation studentEpidemicInformation) throws Exception {
        examineService.publishCustomContent(studentEpidemicInformation);
        return ActionReturnUtil.returnSuccess();
    }

}
