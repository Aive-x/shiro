package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dto.AreaEpidemic;
import com.springboot.shiro.service.EpidemicService;
import com.springboot.shiro.service.bean.Area;
import com.springboot.shiro.service.bean.AreaTree;
import com.springboot.shiro.service.bean.EpidemicInformation;
import com.springboot.shiro.util.HttpClientUtil;
import com.springboot.shiro.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/1/17 2:26 下午
 */
@Service
public class EpidemicServiceImpl implements EpidemicService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public EpidemicInformation getEpidemicInformation() throws Exception {
        BoundHashOperations<String, String, String> clusterHashOps = stringRedisTemplate.boundHashOps("epidemic");
        return JsonUtil.jsonToPojo(clusterHashOps.get("epidemic"), EpidemicInformation.class);
    }

    @Override
    public List<AreaEpidemic> getAreaEpidemicInformation() throws Exception {
        EpidemicInformation epidemicInformation = this.getEpidemicInformation();
        AreaTree areaTree = epidemicInformation.getAreaTree().stream().collect(Collectors.toMap(AreaTree::getName, areaTree1 -> areaTree1)).get("中国");
        List<AreaEpidemic> areaEpidemicList = new ArrayList<>();
        areaTree.getChildren().forEach(area -> {
            AreaEpidemic areaEpidemic = new AreaEpidemic();
            areaEpidemic.setArea(area.getName());
            areaEpidemic.setCurrentConfirmedCount(area.getToday().getConfirm());
            areaEpidemic.setConfirmedCount(area.getTotal().getConfirm());
            areaEpidemicList.add(areaEpidemic);
        });
        return areaEpidemicList;
    }

    @Override
    public List<String> getBoardEpidemicInformation() throws Exception {
        EpidemicInformation epidemicInformation = this.getEpidemicInformation();
        /*String currentDiagnosed = "较上日" + epidemicInformation.getCurrentDiagnosedIncr() + "\n"
            + epidemicInformation.getCurrentDiagnosed() + "\n现有确诊";
        
        String diagnosed =
            "较上日" + epidemicInformation.getDiagnosedIncr() + "\n" + epidemicInformation.getDiagnosed() + "\n现有确诊";
        String serious =
            "较上日" + epidemicInformation.getSeriousIncr() + "\n" + epidemicInformation.getSerious() + "\n现有确诊";
        String suspect =
            "较上日" + epidemicInformation.getSuspectIncr() + "\n" + epidemicInformation.getSuspect() + "\n现有确诊";
        String cured = "较上日" + epidemicInformation.getCuredIncr() + "\n" + epidemicInformation.getCured() + "\n现有确诊";
        String death = "较上日" + epidemicInformation.getDeathIncr() + "\n" + epidemicInformation.getDeath() + "\n现有确诊";*/
        List<String> boardEpidemicInformation = new ArrayList<>();
        /*boardEpidemicInformation.add(currentDiagnosed);
        boardEpidemicInformation.add(diagnosed);
        boardEpidemicInformation.add(serious);
        boardEpidemicInformation.add(suspect);
        boardEpidemicInformation.add(cured);
        boardEpidemicInformation.add(death);*/
        return boardEpidemicInformation;
    }
}
