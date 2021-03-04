package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.StudentEpidemicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/25 11:18 上午
 */
@Service
public class StudentEpidemicInfoServiceImpl implements StudentEpidemicInfoService {

    @Autowired
    private StudentEpidemicInformationMapper studentEpidemicInformationMapper;

    @Override
    public List<StudentEpidemicInformation> getStudentEpidemicInfomation() throws Exception {
        return studentEpidemicInformationMapper.listStudentEpidemicInformation();
    }
}
