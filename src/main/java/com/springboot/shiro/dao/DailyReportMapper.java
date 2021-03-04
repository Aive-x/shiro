package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.DailyReport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/24 3:42 下午
 */
@Repository
public interface DailyReportMapper {

    void addDailyReport(DailyReport dailyReport);

    List<DailyReport> listDailyReport();

    List<DailyReport> getDailyReportByStudentNumber(String studentNumber);
}
