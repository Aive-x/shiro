package com.springboot.shiro.service.cache;

import com.springboot.shiro.service.EpidemicService;
import com.springboot.shiro.service.bean.EpidemicInformation;
import com.springboot.shiro.util.BeanContext;
import com.springboot.shiro.util.HttpClientUtil;
import com.springboot.shiro.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xutianhong
 * @Date 2021/1/17 3:06 下午
 */
@Slf4j
@Component
public class EpidemicCacheManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostConstruct
    public void initEpidemicInformation() throws Exception{
        log.info("初始化疫情信息");
        EpidemicInformation epidemicInformation = this.getEpidemicInformation();
        BoundHashOperations<String, String, String> clusterHashOps = stringRedisTemplate.boundHashOps("epidemic");
        clusterHashOps.put("epidemic", JsonUtil.convertToJson(epidemicInformation));
        log.info("疫情信息存入redis成功");
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(new MyScheduledExecutor(), 5, 30, TimeUnit.MINUTES);
    }

    public EpidemicInformation getEpidemicInformation() throws Exception{
        Map<String, Object> result = HttpClientUtil.httpGetClient("c.m.163.com/ug/api/wuhan/app/data/list-total", null, null);
        return JsonUtil.jsonToPojo(
                JsonUtil.convertToJson(JsonUtil.convertJsonToMap(result.get("body").toString()).get("data")),
                EpidemicInformation.class);
    }

}
