package com.springboot.shiro.service.Impl;

import com.springboot.shiro.service.NewsService;
import com.springboot.shiro.service.bean.News;
import com.springboot.shiro.service.bean.NewsList;
import com.springboot.shiro.util.HttpClientUtil;
import com.springboot.shiro.util.JsonUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author weiyifan
 * @Date 2021/3/22 11:00 中午
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Override
    public List<News> getNews() throws Exception {

        //自定义url
        String url = "api.tianapi.com/txapi/ncov/index?key=d6c9716cc3c2444a8e4924d34baabb0f";
        //进行http的get请求
        Map<String, Object> result = HttpClientUtil.httpGetClient(url, null, null);
        //将获取到的json返回数据，转换为对象
        List<NewsList> newsList = JsonUtil.jsonToList(
            JsonUtil.convertToJson(JsonUtil.convertJsonToMap(result.get("body").toString()).get("newslist")),
            NewsList.class);
        //非空判断
        assert newsList != null;
        //返回news
        return newsList.get(0).getNews();

    }

}
