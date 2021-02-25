package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.DailyReport;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/24 4:04 下午
 */
public interface DailyReportService {
    void addDailyReport(DailyReport dailyReport) throws Exception;

    List<DailyReport> listDailyReport() throws Exception;
}
