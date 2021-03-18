package com.springboot.shiro.dao.bean;

import lombok.Data;

/**
 * @author xutianhong
 * @Date 2021/3/5 5:29 下午
 */
@Data
public class JwcAccount {
    private Integer id;
    private String studentNumber;
    private String jwcUsername;
    private String jwcPassword;
    private Integer bind;
}
