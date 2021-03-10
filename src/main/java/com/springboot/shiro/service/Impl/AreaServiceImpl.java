package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.AreaMapper;
import com.springboot.shiro.service.AreaService;
import com.springboot.shiro.dao.bean.Area;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/3/10 10:43 上午
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaMapper areaMapper;

    @Override
    public List<String> getCity(String province) throws Exception {
        return areaMapper.getCity(province);
    }

    @Override
    public Map<String, List<Area>> listCities() throws Exception {
        List<Area> areaList = areaMapper.listCities();
        Map<String, List<Area>> areaListMap = areaList.stream().collect(Collectors.groupingBy(Area::getProvince));
        return areaListMap;
    }

    @Override
    public Set<String> getProvince() throws Exception {
        return new HashSet<>(areaMapper.getProvince());
    }
}
