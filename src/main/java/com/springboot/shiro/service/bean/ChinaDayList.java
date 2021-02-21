package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/2/21 4:45 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChinaDayList {
    private String date;

    private Today today;

    private Total total;

    private ExtData extData;

    private String lastUpdateTime;
}
