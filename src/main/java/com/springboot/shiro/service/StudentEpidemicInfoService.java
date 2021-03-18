package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/25 11:18 上午
 */
@Repository
public interface StudentEpidemicInfoService {

    List<StudentEpidemicInformation> getStudentEpidemicInfomation() throws Exception;

    void setStudentEpidemicInfomation(String studentNumber, String others, String person, String place);

    StudentEpidemicInformation getStudentEpidemicInformationById(String id);

    void updatePublish(StudentEpidemicInformation studentEpidemicInformation);

    void publishCustomContent(StudentEpidemicInformation studentEpidemicInformation);
}
