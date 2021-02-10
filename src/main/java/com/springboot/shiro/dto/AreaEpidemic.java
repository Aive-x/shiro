package com.springboot.shiro.dto;

import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/1/27 1:22 下午
 */
@Data
public class AreaEpidemic {
    private String area;
    private Integer currentConfirmedCount;
    private Integer confirmedCount;
}
