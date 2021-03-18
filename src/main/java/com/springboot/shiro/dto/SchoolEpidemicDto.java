package com.springboot.shiro.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/3/17 5:55 下午
 */
@Data
public class SchoolEpidemicDto {
    private Integer id;
    private Date date;
    private String name;
    private String classes;
    private String trip;
    private String tag;
}
