package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.JwcAccount;
import org.springframework.stereotype.Repository;

/**
 * @author xutianhong
 * @Date 2021/3/5 5:31 下午
 */
@Repository
public interface JwcAccountMapper {

    void bindJwcAccount(JwcAccount jwcAccount);

    JwcAccount getJwcAccount(String studentNumber);

}
