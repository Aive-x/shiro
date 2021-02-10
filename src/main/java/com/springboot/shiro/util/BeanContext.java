package com.springboot.shiro.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author xutianhong
 * 在ScheduledExecutorService手动获取bean
 *
 * @Date 2021/2/6 5:47 下午
 */
@Slf4j
@Component
public class BeanContext implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return getApplicationContext().getBean(clazz);
    }

}
