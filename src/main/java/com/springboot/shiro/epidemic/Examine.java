package com.springboot.shiro.epidemic;

import com.springboot.shiro.dao.bean.Trip;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.service.TripService;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.service.bean.Children;
import com.springboot.shiro.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/4 4:03 下午
 */
@Service
public class Examine {

    @Autowired
    private UserService userService;
    @Autowired
    private TripService tripService;
    @Autowired
    private StudentEpidemicInfoService studentEpidemicInfoService;

    public void touchPeople(String studentNumber) {
        User user = userService.getUserByUsername(studentNumber);

        List<User> sameDormitoryStudentList = userService.getSameDormitoryStudent(user.getDormitory(), studentNumber);
        sameDormitoryStudentList.forEach(sameDormitoryStudent -> {
            studentEpidemicInfoService.setStudentEpidemicInfomation(sameDormitoryStudent.getUsername(), null,
                user.getName(), "寝室");
        });
    }

    public void dangerAreaStudent(List<Children> children) {
        children.forEach(child -> {
            if (child.getToday().getConfirm() != null && child.getToday().getConfirm() > 0) {
                // 获取14天内在这个地方待过的学生，并查看是否已经有被推送或发布的记录，如果没有，则推送给老师
                List<Trip> tripList = tripService.getTripByCity(child.getName());
                if (!CollectionUtils.isEmpty(tripList)) {
                    tripList.forEach(trip -> {
                        // 出现符合城市，并且日期在14天内
                        if (trip.getCity().equals(child.getName())
                            && DateUtil.getIntervalDays(DateUtil.getCurrentUtcTime(), trip.getDate()) <= 14) {
                            studentEpidemicInfoService.setStudentEpidemicInfomation(trip.getStudentNumber(),
                                "14天内途经城市出现疫情", null, null);
                        }
                    });
                }
            }
        });
    }
}
