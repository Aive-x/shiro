package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.service.DailyReportService;
import com.springboot.shiro.util.ActionReturnUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author xutianhong
 * @Date 2021/2/24 4:01 下午
 */
@Controller
@Slf4j
@RequestMapping("/daily")
public class DailyReportController {

    @Autowired
    private DailyReportService dailyReportService;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ActionReturnUtil addDailyReport(@RequestParam("studentNumber") String studentNumber,
        @RequestParam("temperature") Double temperature, @RequestParam("position") String position) throws Exception {
        DailyReport dailyReport = new DailyReport();
        dailyReport.setPosition(position);
        dailyReport.setStudentNumber(studentNumber);
        dailyReport.setTemperature(temperature);
        dailyReportService.addDailyReport(dailyReport);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ActionReturnUtil listDailyReport() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(dailyReportService.listDailyReport());
    }
}
