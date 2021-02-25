package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.Classes;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/25 11:59 上午
 */
public interface ClassesMapper {
    List<Classes> listClasses();

    List<Classes> getClasses(String college);
}
