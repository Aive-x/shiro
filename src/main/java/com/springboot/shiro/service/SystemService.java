package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/11 3:38 下午
 */
public interface SystemService {

    String getLog() throws Exception;

    List<StudentEpidemicInformation> getExamine() throws Exception;
}
