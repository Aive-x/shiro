package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.RouteResourceMapper;
import com.springboot.shiro.dao.bean.RouteResource;
import com.springboot.shiro.service.RouteResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/25 14:39
 */
@Service
public class RouteResourceServiceImpl implements RouteResourceService {

    @Autowired
    private RouteResourceMapper routeResourceMapper;

    @Override
    public List<RouteResource> listRouteResource() {
        return null;
    }

    @Override
    public List<String> getRoleByPath(String path) {

        String role = routeResourceMapper.getRoleByPath(path).getRole();
        String[] roles = role.split(",");
        List<String> listRoles = Arrays.asList(roles);
        return listRoles;
    }
}