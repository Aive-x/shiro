package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/25 11:01 上午
 */
@Repository
public interface StudentEpidemicInformationMapper {
    List<StudentEpidemicInformation> listStudentEpidemicInformation();

    void setStudentEpidemicInformation(StudentEpidemicInformation studentEpidemicInformation);

    StudentEpidemicInformation getStudentEpidemicInformationById(String id);

    void updatePublish(String id);
}
