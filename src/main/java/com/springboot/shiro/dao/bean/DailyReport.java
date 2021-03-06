package com.springboot.shiro.dao.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/2/24 2:19 下午
 */
@Data
public class DailyReport implements Serializable {
    private Integer id;
    private String studentNumber;
    private Double temperature;
    private String province;
    private String city;
    private String position;
    private Date date;
}
