package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/1/16 2:05 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EpidemicInformation {

    /**
     * 中国总计
     */
    private ChinaTotal chinaTotal;
    /**
     * 中国每日记录
     */
    private List<ChinaDayList> chinaDayList;
    /**
     * 更新时间
     */
    private String lastUpdateTime;
    /**
     * ？
     */
    private String overseaLastUpdateTime;
    /**
     * 地区信息
     */
    private List<AreaTree> areaTree;
}
