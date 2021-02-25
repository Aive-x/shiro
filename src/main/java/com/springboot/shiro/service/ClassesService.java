package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.Classes;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/25 12:25 下午
 */
public interface ClassesService {
    List<Classes> listClasses();

    List<Classes> getClasses(String college);
}
