package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.ClassesMapper;
import com.springboot.shiro.dao.bean.Classes;
import com.springboot.shiro.service.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/2/25 12:25 下午
 */
@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesMapper classesMapper;

    @Override
    public List<Classes> listClasses() {
        return classesMapper.listClasses();
    }

    @Override
    public List<Classes> listClasses(String ids) {
        List<Classes> classesList = this.listClasses();
        String[] idList = ids.split(",");
        classesList = classesList.stream()
            .filter(classes -> Arrays.stream(idList).anyMatch(id -> id.equals(String.valueOf(classes.getId()))))
            .collect(Collectors.toList());
        return classesList;
    }

    @Override
    public List<Classes> getClasses(String college) {
        return classesMapper.getClasses(college);
    }
}
