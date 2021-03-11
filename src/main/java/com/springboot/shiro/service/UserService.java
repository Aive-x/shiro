package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.User;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:58
 */
public interface UserService {

    User getUserById(Integer id);

    User getUserByUsername(String username);

    List<User> getSameDormitoryStudent(String studentNumber, String dormitory);

    void updatePassword(String username, String password);

}
