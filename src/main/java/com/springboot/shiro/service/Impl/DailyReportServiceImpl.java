package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.DailyReportMapper;
import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.TripMapper;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.DailyReport;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.dao.bean.Trip;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.DailyReportService;
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
    private UserMapper userMapper;
    @Autowired
    private StudentEpidemicInformationMapper studentEpidemicInformationMapper;

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
            User user = userMapper.getUserByUsername(dailyReport.getStudentNumber());
            StudentEpidemicInformation studentEpidemicInformation = new StudentEpidemicInformation();
            studentEpidemicInformation.setDate(dailyReport.getDate());
            studentEpidemicInformation.setClasses(user.getClasses());
            studentEpidemicInformation.setStudentNumber(dailyReport.getStudentNumber());
            studentEpidemicInformation.setName(user.getName());
            studentEpidemicInformation.setOthers("体温高于正常体温");
            studentEpidemicInformation.setTag("疑似");
            studentEpidemicInformationMapper.setStudentEpidemicInformation(studentEpidemicInformation);

            // todo 调取方法，获取各类疑似人员

        }

    }

    @Override
    public List<DailyReport> listDailyReport() throws Exception {
        return dailyReportMapper.listDailyReport();
    }
}
