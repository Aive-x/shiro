package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.User;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:58
 */
public interface UserService {

    User getUserById(Integer id);

    User getUserByUsername(String username);

}
