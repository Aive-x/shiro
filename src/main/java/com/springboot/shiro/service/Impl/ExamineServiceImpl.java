package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.SchoolEpidemicMapper;
import com.springboot.shiro.dao.StudentEpidemicInformationMapper;
import com.springboot.shiro.dao.bean.SchoolEpidemic;
import com.springboot.shiro.dao.bean.StudentEpidemicInformation;
import com.springboot.shiro.service.ExamineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:51 下午
 */
@Service
public class ExamineServiceImpl implements ExamineService {

    @Autowired
    private SchoolEpidemicMapper schoolEpidemicMapper;
    @Autowired
    private StudentEpidemicInformationMapper studentEpidemicInformationMapper;

    @Override
    public void publishStudentEpidemic(String ids) throws Exception {
        String[] idList = ids.split(",");
        for (int i = 0; i < idList.length; ++i) {
            StudentEpidemicInformation studentEpidemicInformation =
                studentEpidemicInformationMapper.getStudentEpidemicInformationById(idList[i]);
            SchoolEpidemic schoolEpidemic = new SchoolEpidemic();
            schoolEpidemic.setDate(studentEpidemicInformation.getDate());
            schoolEpidemic.setName(studentEpidemicInformation.getName());
            schoolEpidemic.setTrip("");
            schoolEpidemic.setTag(studentEpidemicInformation.getTag());
            schoolEpidemicMapper.addSchooleEpidemic(schoolEpidemic);
        }

    }
}
