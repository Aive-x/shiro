package com.springboot.shiro.service.cache;

import com.springboot.shiro.service.bean.EpidemicInformation;
import com.springboot.shiro.util.BeanContext;
import com.springboot.shiro.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author xutianhong
 * @Date 2021/1/20 2:44 下午
 */
@Slf4j
public class MyScheduledExecutor implements Runnable {

    private EpidemicCacheManager epidemicCacheManager;
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void run() {
        try {
            log.info("更新疫情信息");

            this.epidemicCacheManager = BeanContext.getBean(EpidemicCacheManager.class);
            this.stringRedisTemplate = BeanContext.getBean(StringRedisTemplate.class);

            EpidemicInformation epidemicInformation = epidemicCacheManager.getEpidemicInformation();
            BoundHashOperations<String, String, String> clusterHashOps = stringRedisTemplate.boundHashOps("epidemic");
            clusterHashOps.put("epidemic", JsonUtil.convertToJson(epidemicInformation));
        } catch (Exception e){
            log.error("更新疫情信息失败，调取第三方疫情接口出错； e: {}", e);
        }
    }
}
