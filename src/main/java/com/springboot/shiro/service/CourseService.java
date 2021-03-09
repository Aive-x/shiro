package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.service.bean.Course;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/5 5:39 下午
 */
public interface CourseService {

    boolean jwcBind(JwcAccount jwcAccount) throws Exception;

    boolean doJwcLogin(JwcAccount jwcAccount) throws Exception;

    List<Course> listCourse(JwcAccount jwcAccount) throws Exception;

}
