package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.Trip;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/4 4:52 下午
 */
@Repository
public interface TripMapper {

    void setTrip(Trip trip);

    List<Trip> listTrip(String StudentNumber);
}
