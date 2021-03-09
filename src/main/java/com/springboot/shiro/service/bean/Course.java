package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/3/8 3:39 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    private String name;

    private String type;

    private String classRoom;

    private String teacher;

    private int startWeek;

    private int endWeek;

    private int weekState;

    private String number;

    private String day;

}
