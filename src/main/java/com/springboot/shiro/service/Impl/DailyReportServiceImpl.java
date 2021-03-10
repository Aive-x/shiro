package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.DailyReportMapper;
import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.TripMapper;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.dao.bean.Trip;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.epidemic.Examine;
import com.springboot.shiro.service.DailyReportService;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    @Autowired
    private TripMapper tripMapper;
    @Autowired
    private StudentEpidemicInfoService studentEpidemicInfoService;
    @Autowired
    private Examine examine;

    @Override
    public void addDailyReport(DailyReport dailyReport) throws Exception {

        List<DailyReport> dailyReportList =
            dailyReportMapper.getDailyReportByStudentNumber(dailyReport.getStudentNumber());
        dailyReport.setDate(DateUtil.getCurrentUtcTime());

        // 所在地发生变化，写入行程表
        if (!CollectionUtils.isEmpty(dailyReportList)
            && !dailyReport.getPosition().equals(dailyReportList.get(dailyReportList.size() - 1).getPosition())) {
            Trip trip = new Trip();
            trip.setStudentNumber(dailyReport.getStudentNumber());
            trip.setPosition(dailyReport.getPosition());
            trip.setDate(dailyReport.getDate());
            tripMapper.setTrip(trip);
        }
        dailyReportMapper.addDailyReport(dailyReport);

        if (dailyReport.getTemperature() > 37.3) {
            studentEpidemicInfoService.setStudentEpidemicInfomation(dailyReport.getStudentNumber(), "体温高于正常体温", null,
                null);
            examine.touchPeople(dailyReport.getStudentNumber());
        }

    }

    @Override
    public List<DailyReport> listDailyReport() throws Exception {
        return dailyReportMapper.listDailyReport();
    }
}
