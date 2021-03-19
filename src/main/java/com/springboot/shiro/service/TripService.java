package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.Trip;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/19 10:09 上午
 */
public interface TripService {

    void setTrip(Trip trip);

    List<Trip> listTrip(String studentNumber);

    List<Trip> getTripByCity(String city);

}
