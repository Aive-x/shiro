package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.service.EpidemicService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xutianhong
 * @Date 2021/1/27 1:25 下午
 */
@Controller
@Slf4j
@RequestMapping("/epidemic")
public class EpidemicController {

    @Autowired
    private EpidemicService epidemicService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ActionReturnUtil getEpidemicInformation() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(epidemicService.getEpidemicInformation());
    }

    @ResponseBody
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public ActionReturnUtil getAreaEpidemicInformation() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(epidemicService.getAreaEpidemicInformation());
    }

    @ResponseBody
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public ActionReturnUtil getBoardEpidemicInformation() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(epidemicService.getBoardEpidemicInformation());
    }

    @ResponseBody
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public ActionReturnUtil getSchoolEpidemicInformation() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(epidemicService.getSchoolEpidemicInformation());
    }

    @ResponseBody
    @RequestMapping(value = "/school/detail", method = RequestMethod.GET)
    public ActionReturnUtil getSchoolEpidemicInformationDetail(@RequestParam("id") String id) throws Exception {
        return ActionReturnUtil.returnSuccessWithData(epidemicService.getSchoolEpidemicInfoDetail(id));
    }

}
