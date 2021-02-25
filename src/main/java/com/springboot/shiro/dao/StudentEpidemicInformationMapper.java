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
    List<StudentEpidemicInformation> getStudentEpidemicInformation();

    void setStudentEpidemicInformation(StudentEpidemicInformation studentEpidemicInformation);
}
