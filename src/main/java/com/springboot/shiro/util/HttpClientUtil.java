package com.springboot.shiro.util;

import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/1/16 4:28 下午
 */
@Slf4j
public class HttpClientUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClientUtil.class);
    private static int TIMEOUT = 30000;
    private static String UTF_8 = "UTF-8";

    public static Map<String, Object> httpGetClient(String url, Map<String, Object> headers, Map<String, Object> params)
            throws Exception {

        URIBuilder ub = new URIBuilder();
        ub.setScheme("https");
        ub.setHost(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        CloseableHttpResponse response = null;
        if (params != null) {
            ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
            ub.setParameters(pairs);
        }
        try {
            // 设置请求和传输超时时间
            RequestConfig requestConfig =
                    RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();
            HttpGet httpGet = new HttpGet(ub.build());
            httpGet.setConfig(requestConfig);
            if (headers != null) {
                for (Map.Entry<String, Object> param : headers.entrySet()) {
                    httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
                }
            }
            response = httpClient.execute(httpGet);
            return handleResult(response);
        } catch (Exception e) {
            LOGGER.error("http get error,url:{}", url, e);
            throw e;
        } finally {
            try {
                if (null != response) {
                    EntityUtils.consume(response.getEntity());
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LOGGER.error("http client close error,url:{}", e);
            }
        }
    }

    public static Map<String, Object> httpPutClient(String url, Map<String, Object> headers, Map<String, Object> params,
                                                    String json) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {

            // 设置请求和传输超时时间
            RequestConfig requestConfig =
                    RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();
            HttpPut httpPut = new HttpPut(url);
            httpPut.setConfig(requestConfig);
            if (headers != null) {
                for (Map.Entry<String, Object> param : headers.entrySet()) {
                    httpPut.addHeader(param.getKey(), String.valueOf(param.getValue()));
                }
            }
            if (params != null) {
                HttpEntity entity = new StringEntity(JsonUtil.objectToJson(params), "utf-8");
                httpPut.setEntity(entity);
            } else if (json != null) {
                StringEntity se = new StringEntity(json);
                se.setContentType("test/json");
                httpPut.setEntity(se);
            }
            CloseableHttpResponse response = httpClient.execute(httpPut);
            return handleResult(response);
        } catch (Exception e) {
            LOGGER.error("http get error,url:{}", url, e);
            throw e;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LOGGER.error("http client close error,url:{}", e);
            }
        }
    }

    public static Map<String, Object> httpDeleteClient(String url, Map<String, Object> headers,
                                                       Map<String, Object> params) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        if (params != null) {
            ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
            ub.setParameters(pairs);
        }
        try {
            // 设置请求和传输超时时间
            RequestConfig requestConfig =
                    RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();
            HttpDelete httpDelete = new HttpDelete(ub.build());
            httpDelete.setConfig(requestConfig);
            if (headers != null) {
                for (Map.Entry<String, Object> param : headers.entrySet()) {
                    httpDelete.addHeader(param.getKey(), String.valueOf(param.getValue()));
                }
            }
            CloseableHttpResponse response = httpClient.execute(httpDelete);
            return handleResult(response);
        } catch (Exception e) {
            LOGGER.error("http get error,url:{}", url, e);
            throw e;
        } finally {
            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                LOGGER.error("http client close error,url:{}", e);
            }
        }
    }

    public static Map<String, Object> httpPostClient(String url, Map<String, Object> headers,
                                                     Map<String, Object> params, String json) throws Exception {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            // 设置请求和传输超时时间
            RequestConfig requestConfig =
                    RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();
            HttpPost httpPost = new HttpPost(ub.build());
            httpPost.setConfig(requestConfig);
            if (headers != null) {
                for (Map.Entry<String, Object> param : headers.entrySet()) {
                    httpPost.addHeader(param.getKey(), String.valueOf(param.getValue()));
                }
            }
            if (params != null) {
                HttpEntity entity = new StringEntity(JsonUtil.objectToJson(params), "utf-8");
                httpPost.setEntity(entity);
            } else if (json != null) {
                StringEntity se = new StringEntity(json);
                se.setContentType("test/json");
                httpPost.setEntity(se);
            }
            CloseableHttpResponse response = httpClient.execute(httpPost);
            return handleResult(response);
        } catch (Exception e) {
            LOGGER.error("http get error,url:{}", url, e);
            throw e;
        } finally {
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    public static String getContentCharSet(final HttpEntity entity) {
        String charset = "UTF-8";
        if (entity == null) {
            return charset;
        }
        if (entity.getContentType() != null) {
            HeaderElement values[] = entity.getContentType().getElements();
            if (values.length > 0) {
                NameValuePair param = values[0].getParameterByName("charset");
                if (param != null) {
                    charset = param.getValue();
                }
            }
        }
        return charset;
    }

    /**
     * 将map转换为请求中的参数
     *
     * @param params
     * @return
     */
    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }
        return pairs;
    }

    public static String getApiKey(String username, String password) throws Exception {
        String apiKey = "Basic " + new String(Base64.encodeBase64((username + ":" + password).getBytes()));
        return apiKey;
    }

    public static Map<String, Object> handleResult(CloseableHttpResponse response) throws Exception {
        Map<String, Object> resultMap = new HashMap<>(2);
        Integer statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            String charset = getContentCharSet(entity);
            // 使用EntityUtils的toString方法，传递编码，默认编码是UTF-8
            resultMap.put("body", EntityUtils.toString(entity, charset));
        }
        if (!String.valueOf(statusCode).startsWith("2")) {
            throw new MarsRuntimeException(ErrorCodeMessage.UNKNOWN);
        }
        resultMap.put("status", String.valueOf(statusCode));
        return resultMap;
    }
}
