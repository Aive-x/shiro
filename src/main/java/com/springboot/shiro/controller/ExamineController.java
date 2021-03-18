package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.ExamineService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
                                                   @RequestParam("tags") String tags) throws Exception {
        examineService.publishStudentEpidemic(ids, tags);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/custom", method = RequestMethod.POST)
    public ActionReturnUtil publishCustomContent(StudentEpidemicInformation studentEpidemicInformation) throws Exception {
        examineService.publishCustomContent(studentEpidemicInformation);
        return ActionReturnUtil.returnSuccess();
    }

}
