package com.springboot.shiro.dto;

import com.springboot.shiro.dao.bean.SchoolEpidemic;
import lombok.Data;

import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/3/19 11:20 上午
 */
@Data
public class SchoolEpidemicDetailDto extends SchoolEpidemic {

    private String name;
    private String classes;
    private Date date;
    private String trip;
    private String operator;

}
