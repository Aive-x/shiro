package com.springboot.shiro.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/3/15 2:52 下午
 */
@Data
public class DailyReportDto {
    private Integer id;
    private String studentNumber;
    private Double temperature;
    private String province;
    private String city;
    private String position;
    private Date date;
    private boolean today;
}
