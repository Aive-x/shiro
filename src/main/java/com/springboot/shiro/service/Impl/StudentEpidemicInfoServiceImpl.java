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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<StudentEpidemicInformation> getStudentEpidemicInformationByPage(String page) throws Exception {
        List<StudentEpidemicInformation> studentEpidemicInformationList = this.getStudentEpidemicInformation(false);
        List<StudentEpidemicInformation> result = new ArrayList<>();
        Integer pageInt = Integer.parseInt(page);
        if (pageInt * 15 > studentEpidemicInformationList.size()){
            for (int i = 0; i < studentEpidemicInformationList.size(); ++i){
                if (i + 1 >= (pageInt - 1) * 15 && i + 1 <= pageInt * 15 ){
                    result.add(studentEpidemicInformationList.get(i));
                }
            }
        }
        return result;
    }

    @Override
    public List<StudentEpidemicInformation> getStudentEpidemicInformation(boolean isPublished) throws Exception {
        List<StudentEpidemicInformation> studentEpidemicInformationList =
            studentEpidemicInformationMapper.listStudentEpidemicInformation();
        //isPublished == true ? 全部 : 未发布
        if (!isPublished) {
            studentEpidemicInformationList = studentEpidemicInformationList.stream()
                .filter(studentEpidemicInformation -> studentEpidemicInformation.getIsPublished() == 0)
                .collect(Collectors.toList());
        }
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
