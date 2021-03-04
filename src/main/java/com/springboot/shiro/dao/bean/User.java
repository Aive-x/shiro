package com.springboot.shiro.dao.bean;

import lombok.Data;

/**
 * @author xutianhong
 * @Date 2020/9/23 15:20
 */
@Data
public class User implements java.io.Serializable{

    private Integer id;
    private String username;
    private String password;
    private String name;
    private String college;
    private String classes;
    private String dormitory;
    private String role;
    private String phone;

    @Override
    public String toString(){
        return "user{" +
                "id='" + id + '\'' +
                ", login='" + username + '\'' +
                ", password='" + password +
                ", role'" + role + '\'' +
                '}';
    }
}
