package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.dto.DailyReportDto;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/24 4:04 下午
 */
public interface DailyReportService {
    void addDailyReport(DailyReportDto dailyReportDto) throws Exception;

    List<DailyReport> listDailyReport() throws Exception;

    DailyReportDto getReportHistory(String studentNumber) throws Exception;
}
