package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.TripMapper;
import com.springboot.shiro.dao.bean.Trip;
import com.springboot.shiro.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/3/19 10:09 上午
 */
@Service
public class TripServiceImpl implements TripService {

    @Autowired
    private TripMapper tripMapper;

    @Override
    public void setTrip(Trip trip) {
        tripMapper.setTrip(trip);
    }

    @Override
    public List<Trip> listTrip(String studentNumber) {
        return tripMapper.listTrip(studentNumber);
    }

    @Override
    public List<Trip> getTripByCity(String city) {
        return tripMapper.getTripByCity(city);
    }
}
