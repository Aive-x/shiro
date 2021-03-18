package com.springboot.shiro.service.Impl;

import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.JwcAccountMapper;
import com.springboot.shiro.dao.SchoolEpidemicMapper;
import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.dao.bean.SchoolEpidemic;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.CourseService;
import com.springboot.shiro.service.ExamineService;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.service.bean.Course;
import com.springboot.shiro.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:51 下午
 */
@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    private SchoolEpidemicMapper schoolEpidemicMapper;
    @Autowired
    private StudentEpidemicInfoService studentEpidemicInfoService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @Override
    public void publishStudentEpidemic(String ids, String tags) throws Exception {
        String[] idList = ids.split(",");
        String[] tagList = tags.split(",");
        for (int i = 0; i < idList.length; ++i) {
            StudentEpidemicInformation studentEpidemicInformation =
                studentEpidemicInfoService.getStudentEpidemicInformationById(idList[i]);
            // 生成schoolEpidemic数据，并写入数据库
            this.addSchoolEpidemic(studentEpidemicInformation, tagList[i]);
            studentEpidemicInformation.setTag(tagList[i]);
            studentEpidemicInfoService.updatePublish(studentEpidemicInformation);
        }
    }

    @Override
    public void publishCustomContent(StudentEpidemicInformation studentEpidemicInformation) throws Exception {
        if (StringUtils.isEmpty(studentEpidemicInformation.getStudentNumber())){
            throw new MarsRuntimeException(ErrorCodeMessage.STUDENT_NUMBER_EMPTY);
        }
        if (StringUtils.isEmpty(studentEpidemicInformation.getTag())){
            throw new MarsRuntimeException(ErrorCodeMessage.TAG_EMPTY);
        }
        User user = userService.getUserByUsername(studentEpidemicInformation.getStudentNumber());
        studentEpidemicInformation.setDate(DateUtil.getCurrentUtcTime());
        studentEpidemicInformation.setClasses(user.getClasses());
        studentEpidemicInformation.setName(user.getName());
        studentEpidemicInformation.setIsPublished(0);
        studentEpidemicInfoService.publishCustomContent(studentEpidemicInformation);
    }

    public void addSchoolEpidemic(StudentEpidemicInformation studentEpidemicInformation, String tag) throws Exception {
        SchoolEpidemic schoolEpidemic = new SchoolEpidemic();
        schoolEpidemic.setDate(studentEpidemicInformation.getDate());
        schoolEpidemic.setName(studentEpidemicInformation.getName());
        // 获取教务处账号用于登陆教务管理系统
        JwcAccount jwcAccount = courseService.getJwcAccount(studentEpidemicInformation.getStudentNumber());
        // 获取课程列表
        List<Course> courseList = courseService.listCourse(jwcAccount);
        // 整理课程列表信息
        Map<String, List<Course>> courseListMap = courseList.stream().collect(Collectors.groupingBy(Course::getDay));
        List<String> tripList = new ArrayList<>();

        courseListMap.forEach((day, courses) -> {
            String dayTrip = "";
            switch (day) {
                case "周一":
                    dayTrip = "1" + day + ":\n";
                    break;
                case "周二":
                    dayTrip = "2" + day + ":\n";
                    break;
                case "周三":
                    dayTrip = "3" + day + ":\n";
                    break;
                case "周四":
                    dayTrip = "4" + day + ":\n";
                    break;
                case "周五":
                    dayTrip = "5" + day + ":\n";
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + day);
            }
            // tring dayTrip = day + ":\n";
            for (Course course : courses) {
                dayTrip = dayTrip + course.getNumber() + "   "
                    + course.getClassRoom().substring(0, course.getClassRoom().indexOf("楼") + 4) + "\n";
            }
            tripList.add(dayTrip);
        });
        String trip = "";
        Collections.sort(tripList);
        for (String dayTrip : tripList) {
            trip = trip + dayTrip;
        }

        schoolEpidemic.setTrip(trip);
        schoolEpidemic.setTag(tag);
        schoolEpidemicMapper.addSchoolEpidemic(schoolEpidemic);
    }
}
