package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.DailyReportMapper;
import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.service.DailyReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/24 4:04 下午
 */
@Slf4j
@Service
public class DailyReportServiceImpl implements DailyReportService {

    @Autowired
    private DailyReportMapper dailyReportMapper;

    @Override
    public void addDailyReport(DailyReport dailyReport) throws Exception {
        dailyReportMapper.addDailyReport(dailyReport);
    }

    @Override
    public List<DailyReport> listDailyReport() throws Exception {
        return dailyReportMapper.listDailyReport();
    }
}
