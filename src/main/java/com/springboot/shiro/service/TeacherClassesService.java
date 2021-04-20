package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.TeacherClasses;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/4/20 4:32 下午
 */
public interface TeacherClassesService {

    List<String> listTeacherClasses(String username);

    TeacherClasses getTeacherClasses(String username);

    void setTeacherClasses(String username, String ids);

}
