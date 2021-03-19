package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.AreaMapper;
import com.springboot.shiro.dao.SchoolEpidemicMapper;
import com.springboot.shiro.dao.bean.SchoolEpidemic;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.dto.AreaEpidemic;
import com.springboot.shiro.dto.SchoolEpidemicDetailDto;
import com.springboot.shiro.epidemic.Examine;
import com.springboot.shiro.service.EpidemicService;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.service.bean.*;
import com.springboot.shiro.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/1/17 2:26 下午
 */
@Service
public class EpidemicServiceImpl implements EpidemicService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private SchoolEpidemicMapper schoolEpidemicMapper;
    @Autowired
    private Examine examine;
    @Autowired
    private UserService userService;

    @Override
    public EpidemicInformation getEpidemicInformation() throws Exception {
        BoundHashOperations<String, String, String> clusterHashOps = stringRedisTemplate.boundHashOps("epidemic");
        return JsonUtil.jsonToPojo(clusterHashOps.get("epidemic"), EpidemicInformation.class);
    }

    @Override
    public List<AreaEpidemic> getAreaEpidemicInformation() throws Exception {
        EpidemicInformation epidemicInformation = this.getEpidemicInformation();
        AreaTree areaTree = epidemicInformation.getAreaTree().stream()
            .collect(Collectors.toMap(AreaTree::getName, areaTree1 -> areaTree1)).get("中国");
        List<AreaEpidemic> areaEpidemicList = new ArrayList<>();
        areaTree.getChildren().forEach(area -> {
            AreaEpidemic areaEpidemic = new AreaEpidemic();
            areaEpidemic.setArea(area.getName());
            areaEpidemic.setCurrentConfirmedCount(area.getToday().getConfirm());
            // 发现新增患者的城市，查询是否有学生存在
            if (area.getToday().getConfirm() > 0) {
                examine.dangerAreaStudent(area.getChildren());
            }
            areaEpidemic.setConfirmedCount(area.getTotal().getConfirm());
            areaEpidemicList.add(areaEpidemic);
        });
        return areaEpidemicList;
    }

    public boolean filterArea(Children children) {
        String filter = "境外输入\n" + "任城监狱\n" + "境外输入人员\n" + "地市待确认\n" + "待确认\n" + "监狱系统\n" + "中沙群岛的岛礁及其海域\n" + "本土病例\n"
            + "境外来沪\n" + "外地来沪\n" + "十里丰监狱\n" + "划归外省\n" + "未明确地区\n" + "地区待确诊\n" + "地区待确定" + "十里丰监狱";
        return !filter.contains(children.getName());
    }

    @Override
    public List<String> getBoardEpidemicInformation() throws Exception {
        EpidemicInformation epidemicInformation = this.getEpidemicInformation();
        ChinaTotal chinaTotal = epidemicInformation.getChinaTotal();
        List<ChinaDayList> chinaDayList = epidemicInformation.getChinaDayList();

        Total total = chinaTotal.getTotal();
        Today today = chinaTotal.getToday();
        ExtData extData = chinaTotal.getExtData();

        String currentDiagnosed;
        String diagnosed;
        String suspect;
        String cured;
        String death;
        String input;
        int nowConfirm;
        int incinput;

        nowConfirm = total.getConfirm() - total.getDead() - total.getHeal();
        incinput = total.getInput() - chinaDayList.get(chinaDayList.size() - 1).getTotal().getInput();

        if (today.getConfirm() == null) {
            currentDiagnosed = "较上日" + "+0" + "\n" + total.getConfirm() + "\n现有确诊";
        } else if (today.getStoreConfirm().compareTo(0) == 1) {
            currentDiagnosed = "较上日" + "+" + today.getStoreConfirm() + "\n" + nowConfirm + "\n现有确诊";
        } else {
            currentDiagnosed = "较上日" + today.getStoreConfirm() + "\n" + nowConfirm + "\n现有确诊";
        }

        if (today.getConfirm() == null) {
            diagnosed = "较上日" + "+0" + "\n" + total.getConfirm() + "\n累计确诊";
        } else if (today.getConfirm().compareTo(0) == 1) {
            diagnosed = "较上日" + "+" + today.getConfirm() + "\n" + total.getConfirm() + "\n累计确诊";
        } else {
            diagnosed = "较上日" + today.getConfirm() + "\n" + total.getConfirm() + "\n累计确诊";
        }

        if (extData.getIncrNoSymptom() == null) {
            suspect = "较上日" + "+0" + "\n" + extData.getNoSymptom() + "\n无症状感染者";
        } else if (extData.getIncrNoSymptom().compareTo(0) == 1) {
            suspect = "较上日" + "+" + extData.getIncrNoSymptom() + "\n" + extData.getNoSymptom() + "\n无症状感染者";
        } else {
            suspect = "较上日" + extData.getIncrNoSymptom() + "\n" + extData.getNoSymptom() + "\n无症状感染者";
        }

        if (today.getHeal() == null) {
            cured = "较上日" + "+0" + "\n" + total.getHeal() + "\n累计治愈";
        } else if (today.getHeal().compareTo(0) == 1) {
            cured = "较上日" + "+" + today.getHeal() + "\n" + total.getHeal() + "\n累计治愈";
        } else {
            cured = "较上日" + today.getHeal() + "\n" + total.getHeal() + "\n累计治愈";
        }

        if (today.getDead() == null) {
            death = "较上日" + "+0" + "\n" + total.getDead() + "\n累计死亡";
        } else if (today.getDead().compareTo(0) == 1) {
            death = "较上日" + "+" + today.getDead() + "\n" + total.getDead() + "\n累计死亡";
        } else {
            death = "较上日" + today.getDead() + "\n" + total.getDead() + "\n累计死亡";
        }

        if (incinput >= 0) {
            input = "较上日" + "+" + incinput + "\n" + total.getInput() + "\n境外输入";
        } else {
            input = "较上日" + incinput + "\n" + total.getInput() + "\n境外输入";
        }

        List<String> boardEpidemicInformation = new ArrayList<>();
        boardEpidemicInformation.add(currentDiagnosed);
        boardEpidemicInformation.add(diagnosed);
        boardEpidemicInformation.add(suspect);
        boardEpidemicInformation.add(input);
        boardEpidemicInformation.add(cured);
        boardEpidemicInformation.add(death);
        return boardEpidemicInformation;
    }

    @Override
    public List<SchoolEpidemic> getSchoolEpidemicInformation() throws Exception {
        List<SchoolEpidemic> schoolEpidemicList = schoolEpidemicMapper.listSchoolEpidemic();
        Collections.reverse(schoolEpidemicList);
        return schoolEpidemicList;
    }

    @Override
    public SchoolEpidemicDetailDto getSchoolEpidemicInfoDetail(String id) {

        SchoolEpidemicDetailDto schoolEpidemicDetailDto = new SchoolEpidemicDetailDto();
        SchoolEpidemic schoolEpidemic = schoolEpidemicMapper.getSchoolEpidemicById(id);
        schoolEpidemicDetailDto.setName(schoolEpidemic.getName());
        schoolEpidemicDetailDto.setDate(schoolEpidemic.getDate());
        schoolEpidemicDetailDto.setOperator(schoolEpidemic.getOperator());
        schoolEpidemicDetailDto.setTrip(schoolEpidemic.getTrip());
        User user = userService.getUserByUsername(schoolEpidemic.getStudentNumber());
        schoolEpidemicDetailDto.setClasses(user.getClasses());
        return schoolEpidemicDetailDto;
    }
}
