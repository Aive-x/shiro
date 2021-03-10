package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.Area;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xutianhong
 * @Date 2021/3/10 10:40 上午
 */
public interface AreaService {

    Set<String> getProvince() throws Exception;

    List<String> getCity(String province) throws Exception;

    Map<String, List<Area>> listCities() throws Exception;
}
