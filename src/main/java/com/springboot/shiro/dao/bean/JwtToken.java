package com.springboot.shiro.dao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author xutianhong
 * @Date 2020/9/23 16:28
 */
@Data
@AllArgsConstructor
public class JwtToken implements AuthenticationToken {

    private String jwt;

    @Override//类似是用户名
    public Object getPrincipal() {
        return jwt;
    }

    @Override//类似密码
    public Object getCredentials() {
        return jwt;
    }
    //返回的都是jwt
}
