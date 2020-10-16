package com.springboot.shiro.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xutianhong
 * @Date 2020/9/24 14:44
 */
@Data
@AllArgsConstructor
public class Response {

    private Integer status;

    private String msg;

    private Object data;
}
