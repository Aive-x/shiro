package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/1/16 2:20 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProvinceCompare {
    private Integer nowConfirm;
    private Integer confirmAdd;
    private Integer dead;
    private Integer heal;
    private Integer zero;
}
