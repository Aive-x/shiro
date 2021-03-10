package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.Area;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/10 9:44 上午
 */
@Repository
public interface AreaMapper {

    void addArea(String province, String city);

    List<String> getProvince();

    List<String> getCity(String province);

    List<Area> listCities();

}
