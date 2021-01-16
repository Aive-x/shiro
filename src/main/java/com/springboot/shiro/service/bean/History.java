package com.springboot.shiro.service.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import java.util.Date;

/**
 * @author xutianhong
 * @Date 2021/1/16 2:08 下午
 */
@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class History {
    /**
     * 日期
     */
    private Date data;
    /**
     * 确诊人数
     */
    private Integer confirmedNum;
    /**
     * 确诊增加
     */
    private Integer confirmedIncr;
    /**
     * 疑似人数
     */
    private Integer suspectedNum;
    /**
     * 治愈人数
     */
    private Integer curesNum;
    /**
     * 死亡人数
     */
    private Integer deathsNum;
    /**
     * 治愈新增
     */
    private Integer suspectedIncr;
    /**
     * 治愈率
     */
    private Integer curesRatio;
    /**
     * 死亡率
     */
    private Integer deathsRatio;
    /**
     * 疑似人数
     */
    private Integer suspectedNumStr;
    /**
     * 疑似新增
     */
    private Integer suspectedIncrStr;
    /**
     * 治疗中人数
     */
    private Integer treatingNum;
    /**
     * 归国人数？
     */
    private Integer inboundNum;
    /**
     * 归国新增？
     */
    private Integer inboundIncr;
    /**
     * 无症状人数
     */
    private Integer asymptomaticNum;
    /**
     * 无症状新增
     */
    private Integer asymptomaticIncr;
}
