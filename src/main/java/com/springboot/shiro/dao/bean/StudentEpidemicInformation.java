package com.springboot.shiro.dao.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/2/25 10:59 上午
 */
@Data
public class StudentEpidemicInformation {
    private Integer id;
    private Date date;
    private String classes;
    private String studentNumber;
    private String name;
    private String person;
    private String place;
    private String others;
    private String tag;
    private Integer isPublished;
}
