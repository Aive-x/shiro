package com.springboot.shiro.service.Impl;


import com.springboot.shiro.service.bean.New;
import com.springboot.shiro.service.bean.News;
import com.springboot.shiro.service.bean.NewsList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weiyifan
 * @Date 2021/3/22 11:00 中午
 */
@Service
public class NewServiceImpl {
    @Override
    public New getNew() throws Exception {
        New nnew = this.getNew();
        NewsList newsList = nnew.getNewsList();
        News news = newsList.getNews();


        List<String> boardNew = new ArrayList<>();
        boardNew.add(news.getId());
        boardNew.add(news.getPubDate());
        boardNew.add(news.getPubDateStr());
        boardNew.add(news.getTitle());
        boardNew.add(news.getSummary());
        boardNew.add(news.getInfoSource());
        boardNew.add(news.getSourceUrl());

    }
}
