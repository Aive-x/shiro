package com.springboot.shiro.service.bean;

import lombok.Data;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2021/2/21 4:45 下午
 */
@Data
public class AreaTree {
    private Today today;

    private Total total;

    private ExtData extData;

    private String name;

    private String id;

    private String lastUpdateTime;

    private List<Children> children;
}
