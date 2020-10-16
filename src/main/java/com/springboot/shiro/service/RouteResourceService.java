package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.RouteResource;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/25 14:38
 */
public interface RouteResourceService {
    List<RouteResource> listRouteResource();

    List<String> getRoleByPath(String path);
}

