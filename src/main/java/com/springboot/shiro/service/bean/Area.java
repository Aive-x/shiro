package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/1/16 2:09 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Area {
    /**
     * 省份名称
     */
    private String provinceName;
    /**
     * 省份简称
     */
    private String provinceShortName;
    /**
     * 当前确诊人数
     */
    private Integer currentConfirmedCount;
    /**
     * 确诊人数
     */
    private Integer confirmedCount;
    /**
     * 疑似人数
     */
    private Integer suspectedCount;
    /**
     * 治愈人数
     */
    private Integer curedCount;
    /**
     * 死亡人数
     */
    private Integer deadCount;
    /**
     * 评论
     */
    private String comment;
    /**
     * 地区id
     */
    private Integer locationId;
    /**
     * 数据下载
     */
    private String statisticsData;
    /**
     * 高危区域数量
     */
    private Integer highDangerCount;
    /**
     * 中危区域数量
     */
    private Integer midDangerCount;
    /**
     * 城市数据
     */
    private List<City> cityList;
    /**
     * 危险区域
     */
    private List<DangerArea> dangerAreaList;
    /**
     * 日期
     */
    private Integer origCurrentConfirmedCount;
    /**
     * 省份全称
     */
    private String preProvinceName;
    /**
     * 昨日新增
     */
    private YesterdayIncreased yesterdayIncreased;
    /**
     * 省份对比？
     */
    private ProvinceCompare provinceCompare;

}
