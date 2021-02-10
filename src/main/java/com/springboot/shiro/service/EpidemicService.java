package com.springboot.shiro.service;

import com.springboot.shiro.dto.AreaEpidemic;
import com.springboot.shiro.service.bean.EpidemicInformation;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/1/17 2:26 下午
 */
public interface EpidemicService {

    EpidemicInformation getEpidemicInformation() throws Exception;

    List<AreaEpidemic> getAreaEpidemicInformation() throws Exception;

    List<String> getBoardEpidemicInformation() throws Exception;

}
