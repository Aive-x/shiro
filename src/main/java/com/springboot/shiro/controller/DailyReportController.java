package com.springboot.shiro.controller;

import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.dao.bean.JwtToken;
import com.springboot.shiro.dto.DailyReportDto;
import com.springboot.shiro.service.DailyReportService;
import com.springboot.shiro.util.ActionReturnUtil;
import com.springboot.shiro.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

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
    @Autowired
    private JwtUtil jwtUtil;

    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ActionReturnUtil addDailyReport(DailyReportDto dailyReportDto) throws Exception {
        dailyReportService.addDailyReport(dailyReportDto);
        return ActionReturnUtil.returnSuccess();
    }

    @ResponseBody
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    public ActionReturnUtil addDailyReport(ServletRequest request) throws Exception {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return ActionReturnUtil.returnSuccessWithData(
            dailyReportService.getReportHistory(jwtUtil.getUsername(httpRequest.getHeader("Authorization"))));
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ActionReturnUtil listDailyReport() throws Exception {
        return ActionReturnUtil.returnSuccessWithData(dailyReportService.listDailyReport());
    }
}
