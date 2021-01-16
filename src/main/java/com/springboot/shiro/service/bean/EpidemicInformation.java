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
     * 作者信息
     */
    private String author;
    /**
     * 接口刷新时间
     */
    private String date;
    /**
     * 当前确诊新增
     */
    private Integer currentDiagnosedIncr;
    /**
     * 当前确诊
     */
    private Integer currentDiagnosed;
    /**
     * 总计确诊
     */
    private Integer diagnosed;
    /**
     * 疑似人员
     */
    private Integer suspect;
    /**
     * 死亡人员
     */
    private Integer death;
    /**
     * 治愈人数
     */
    private Integer cured;
    /**
     * 重症人数
     */
    private Integer serious;
    /**
     * 确诊新增？
     */
    private Integer diagnosedIncr;
    /**
     * 疑似新增
     */
    private Integer suspectIncr;
    /**
     * 死亡新增
     */
    private Integer deathIncr;
    /**
     * 治愈新增
     */
    private Integer curedIncr;
    /**
     * 重症新增
     */
    private Integer seriousIncr;
    /**
     * 各省数据
     */
    private List<String> list;
    /**
     * 历史数据
     */
    private List<History> history;
    /**
     * 各省详细数据
     */
    private List<Area> area;

}
