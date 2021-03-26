package com.springboot.shiro.service;

import com.springboot.shiro.service.bean.News;

import java.util.List;

/**
 * @author weiyifan
 * @Date 2021/3/22 11:00 中午
 */
public interface NewsService {

    List<News> getNews() throws Exception;
}
