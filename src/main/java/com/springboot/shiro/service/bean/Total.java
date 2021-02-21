package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/2/21 4:47 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Total {
    private Integer confirm;
    private Integer suspect;
    private Integer heal;
    private Integer dead;
    private Integer severe;
    private Integer input;
    private Integer storeConfirm;
}
