package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dto.AreaEpidemic;
import com.springboot.shiro.service.EpidemicService;
import com.springboot.shiro.service.bean.*;
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
        ChinaTotal chinaTotal = epidemicInformation.getChinaTotal();
        Total total = chinaTotal.getTotal();
        Today today = chinaTotal.getToday();

        String currentDiagnosed;
        String suspect;
        String newdeth;

        if (today.getConfirm() == null) {
            currentDiagnosed = "新增确诊:" + "0";
        } else {
            currentDiagnosed = "新增确诊:" + today.getConfirm();
        }
        if (today.getSuspect() == null) {
            suspect = "新增疑似:" + "0";
        } else {
            suspect = "新增疑似:" + today.getSuspect();
        }
        if (today.getDead() == null) {
            newdeth = "新增死亡:" + "0";
        } else {
            newdeth = "新增死亡:" + today.getDead();
        }

        String diagnosed = "累计确诊:" + total.getConfirm();
        String input = "境外输入:" + total.getInput();
        String cured = "累计治愈:" + total.getHeal();
        String death = "累计死亡:" + total.getDead();

        List<String> boardEpidemicInformation = new ArrayList<>();
        boardEpidemicInformation.add(currentDiagnosed);
        boardEpidemicInformation.add(diagnosed);
        boardEpidemicInformation.add(suspect);
        boardEpidemicInformation.add(newdeth);
        boardEpidemicInformation.add(input);
        boardEpidemicInformation.add(cured);
        boardEpidemicInformation.add(death);
        return boardEpidemicInformation;
    }
}
