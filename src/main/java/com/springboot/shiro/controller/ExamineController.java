package com.springboot.shiro.controller;

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
    public ActionReturnUtil publishStudentEpidemic(@RequestParam("ids") String ids) throws Exception {
        examineService.publishStudentEpidemic(ids);
        return ActionReturnUtil.returnSuccess();
    }

}