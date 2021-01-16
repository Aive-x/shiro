package com.springboot.shiro.controller;

import com.springboot.shiro.common.ActionReturnUtil;
import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.service.bean.EpidemicInformation;
import com.springboot.shiro.util.HttpClientUtil;
import com.springboot.shiro.util.JsonUtil;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xutianhong
 * @Date 2021/1/16 3:19 下午
 */
@Controller
@RequestMapping("")
public class MainController {

    private static int TIMEOUT = 30000;

    @ResponseBody
    @RequestMapping(value = "/epidemic", method = RequestMethod.GET)
    public ActionReturnUtil getEpidemicInformation() throws Exception{
        String url = "http://tianqiapi.com/api";
        Map<String, Object> params = new HashMap<>();
        params.put("version", "epidemic");
        params.put("appid", "23035354");
        params.put("appsecret", "8YvlPNrz");
        Map<String, Object> result = HttpClientUtil.httpGetClient(url, null, params);
        EpidemicInformation epidemicInformation = JsonUtil.jsonToPojo(JsonUtil.convertToJson(JsonUtil.convertJsonToMap(result.get("body").toString()).get("data")), EpidemicInformation.class);
        return ActionReturnUtil.returnSuccessWithData(epidemicInformation);
    }

}
