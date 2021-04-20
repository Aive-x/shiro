package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.TeacherClassesMapper;
import com.springboot.shiro.dao.bean.Classes;
import com.springboot.shiro.dao.bean.TeacherClasses;
import com.springboot.shiro.service.ClassesService;
import com.springboot.shiro.service.TeacherClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/4/20 4:32 下午
 */
@Slf4j
@Service
public class TeacherClassedServiceImpl implements TeacherClassesService {

    @Autowired
    private TeacherClassesMapper teacherClassesMapper;
    @Autowired
    private ClassesService classesService;

    @Override
    public List<String> listTeacherClasses(String username) {
        TeacherClasses teacherClasses = this.getTeacherClasses(username);
        if (ObjectUtils.isEmpty(teacherClasses)){
            return new ArrayList<>();
        }
        List<Classes> classesList = classesService.listClasses(teacherClasses.getClasses());
        List<String> result = new ArrayList<>();
        classesList.forEach(classes -> {
            result.add(classes.getClasses());
        });
        return result;
    }

    @Override
    public TeacherClasses getTeacherClasses(String username) {
        return teacherClassesMapper.getTeacherClasses(username);
    }

    @Override
    public void setTeacherClasses(String username, String ids) {
        TeacherClasses teacherClasses = this.getTeacherClasses(username);
        if (ObjectUtils.isEmpty(teacherClasses)){
            teacherClasses.setUsername(Integer.parseInt(username));
            getTeacherClasses(ids);
            teacherClassesMapper.setTeacherClasses(teacherClasses);
        }
        else{
            teacherClasses.setClasses(ids);
            teacherClassesMapper.updateTeacherClasses(teacherClasses);
        }
    }
}
