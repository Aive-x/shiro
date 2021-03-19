package com.springboot.shiro.dao.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/3/4 9:31 下午
 */
@Data
public class SchoolEpidemic {
    private Integer id;
    private Date date;
    private String name;
    private String studentNumber;
    private String place;
    private String trip;
    private String tag;
    private String operator;
}
