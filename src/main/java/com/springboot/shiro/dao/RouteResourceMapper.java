package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.RouteResource;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/25 14:38
 */
@Repository
public interface RouteResourceMapper {

    List<RouteResource> listRouteResource();

    RouteResource getRoleByPath(String path);

}