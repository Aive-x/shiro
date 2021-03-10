package com.springboot.shiro.dao.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/3/4 4:52 下午
 */
@Data
public class Trip {
    private String id;
    private String studentNumber;
    private String province;
    private String city;
    private String position;
    private Date date;
}
