package com.springboot.shiro.dao.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author xutianhong
 * @Date 2020/9/25 14:37
 */
@Data
public class RouteResource implements Serializable {

    private Integer id;

    private String path;

    private String role;
}
