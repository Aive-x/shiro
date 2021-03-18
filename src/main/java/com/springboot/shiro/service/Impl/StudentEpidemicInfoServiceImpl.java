package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

import java.util.Collections;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/25 11:18 上午
 */
@Service
public class StudentEpidemicInfoServiceImpl implements StudentEpidemicInfoService {

    @Autowired
    private UserService userService;

    @Autowired
    private StudentEpidemicInformationMapper studentEpidemicInformationMapper;

    @Override
    public List<StudentEpidemicInformation> getStudentEpidemicInfomation() throws Exception {
        List<StudentEpidemicInformation> studentEpidemicInformationList =
            studentEpidemicInformationMapper.listStudentEpidemicInformation();
        Collections.reverse(studentEpidemicInformationList);
        return studentEpidemicInformationList;
    }

    @Override
    public StudentEpidemicInformation getStudentEpidemicInformationById(String id) {
        return studentEpidemicInformationMapper.getStudentEpidemicInformationById(id);
    }

    @Override
    public void setStudentEpidemicInfomation(String studentNumber, String others, String person, String place) {
        User user = userService.getUserByUsername(studentNumber);
        StudentEpidemicInformation studentEpidemicInformation = new StudentEpidemicInformation();
        studentEpidemicInformation.setDate(DateUtil.getCurrentUtcTime());
        studentEpidemicInformation.setClasses(user.getClasses());
        studentEpidemicInformation.setStudentNumber(studentNumber);
        studentEpidemicInformation.setName(user.getName());
        studentEpidemicInformation.setPerson(person);
        studentEpidemicInformation.setPlace(place);
        studentEpidemicInformation.setOthers(others);
        studentEpidemicInformation.setTag("疑似");
        studentEpidemicInformation.setIsPublished(0);
        studentEpidemicInformationMapper.setStudentEpidemicInformation(studentEpidemicInformation);
    }

    @Override
    public void publishCustomContent(StudentEpidemicInformation studentEpidemicInformation) {
        studentEpidemicInformationMapper.setStudentEpidemicInformation(studentEpidemicInformation);
    }

    @Override
    public void updatePublish(StudentEpidemicInformation studentEpidemicInformation) {
        studentEpidemicInformationMapper.updatePublish(studentEpidemicInformation);
    }
}
