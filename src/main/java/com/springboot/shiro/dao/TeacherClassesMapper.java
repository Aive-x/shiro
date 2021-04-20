package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.TeacherClasses;
import org.springframework.stereotype.Repository;

/**
 * @author xutianhong
 * @Date 2021/4/20 4:26 下午
 */
@Repository
public interface TeacherClassesMapper {

    TeacherClasses getTeacherClasses(String username);

    void setTeacherClasses(TeacherClasses teacherClasses);

    void updateTeacherClasses(TeacherClasses teacherClasses);

}
