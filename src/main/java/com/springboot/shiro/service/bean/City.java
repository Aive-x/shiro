package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/1/16 2:16 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {
    private String city;
    private String confirmed;
    private String died;
    private String crued;
    private String confirmedRelative;
    private String curConfirm;
    private String cityCode;
    private String cityName;
    private Integer confirmedCount;
    private Integer confirmAdd;
    private String deadCount;
    private Integer curedCount;
    private Integer currentConfirmedCount;
}
