package com.springboot.shiro.epidemic;

import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/4 4:03 下午
 */
@Service
public class Examine {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentEpidemicInformationMapper studentEpidemicInformationMapper;

    public void touchPeople(String studentNumber) {
        User user = userMapper.getUserByUsername(studentNumber);
        // TODO 获取相同教室上课，同一寝室
        // TODO 发现问题，获取统一教室上课的同学，是否意味着就是要遍历一遍所有人————拉一个线程，每隔一段时间走一遍，写入redis？
        List<User> sameDormitoryStudentList = userMapper.getSameDormitoryStudent(user.getDormitory(), studentNumber);
        sameDormitoryStudentList.forEach(sameDormitoryStudent -> {
            StudentEpidemicInformation sameDormitoryStudentEpidemic = new StudentEpidemicInformation();
            sameDormitoryStudentEpidemic.setDate(DateUtil.getCurrentUtcTime());
            sameDormitoryStudentEpidemic.setClasses(sameDormitoryStudent.getClasses());
            sameDormitoryStudentEpidemic.setStudentNumber(sameDormitoryStudent.getUsername());
            sameDormitoryStudentEpidemic.setName(sameDormitoryStudent.getName());
            sameDormitoryStudentEpidemic.setPerson(user.getName());
            sameDormitoryStudentEpidemic.setPlace("寝室");
            sameDormitoryStudentEpidemic.setTag("疑似");
            studentEpidemicInformationMapper.setStudentEpidemicInformation(sameDormitoryStudentEpidemic);
        });

    }

}
