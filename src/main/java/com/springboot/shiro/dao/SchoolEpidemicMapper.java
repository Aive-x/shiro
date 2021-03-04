package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.SchoolEpidemic;
import org.springframework.stereotype.Repository;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:36 下午
 */
@Repository
public interface SchoolEpidemicMapper {
    void addSchooleEpidemic(SchoolEpidemic schoolEpidemic);
}
