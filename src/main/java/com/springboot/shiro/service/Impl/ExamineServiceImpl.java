package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.JwcAccountMapper;
import com.springboot.shiro.dao.SchoolEpidemicMapper;
import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.dao.bean.SchoolEpidemic;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.CourseService;
import com.springboot.shiro.service.ExamineService;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.service.bean.Course;
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
    private JwcAccountMapper jwcAccountMapper;

    // todo 需要允许老师在发布时修改tag，因此需要传所有内容，而非id
    @Override
    public void publishStudentEpidemic(String ids) throws Exception {
        String[] idList = ids.split(",");
        for (int i = 0; i < idList.length; ++i) {
            StudentEpidemicInformation studentEpidemicInformation =
                studentEpidemicInfoService.getStudentEpidemicInformationById(idList[i]);
            SchoolEpidemic schoolEpidemic = new SchoolEpidemic();
            schoolEpidemic.setDate(studentEpidemicInformation.getDate());
            schoolEpidemic.setName(studentEpidemicInformation.getName());

            // 获取教务处账号用于登陆教务管理系统
            JwcAccount jwcAccount = jwcAccountMapper.getJwcAccount(studentEpidemicInformation.getStudentNumber());
            // 获取课程列表
            List<Course> courseList = courseService.listCourse(jwcAccount);
            // 整理课程列表信息
            Map<String, List<Course>> courseListMap =
                courseList.stream().collect(Collectors.groupingBy(Course::getDay));
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
            schoolEpidemic.setTag(studentEpidemicInformation.getTag());
            schoolEpidemicMapper.addSchoolEpidemic(schoolEpidemic);
            studentEpidemicInfoService.updatePublish(idList[i]);
        }

    }
}
