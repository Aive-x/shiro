package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.SchoolEpidemic;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:36 下午
 */
@Repository
public interface SchoolEpidemicMapper {

    void addSchoolEpidemic(SchoolEpidemic schoolEpidemic);

    List<SchoolEpidemic> listSchoolEpidemic();

    SchoolEpidemic getSchoolEpidemicById(String id);
}
