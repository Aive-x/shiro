package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:49 下午
 */
public interface ExamineService {

    void publishStudentEpidemic(String ids, String tags) throws Exception;

    void publishCustomContent(StudentEpidemicInformation studentEpidemicInformation) throws Exception;
}
