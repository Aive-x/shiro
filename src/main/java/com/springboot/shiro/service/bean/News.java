package com.springboot.shiro.service.bean;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(value=JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class News {
    private String id;
    private String pubDate;
    private String pubDateStr;
    private String title;
    private String summary;
    private String infoSource;
    private String sourceUrl;
}
