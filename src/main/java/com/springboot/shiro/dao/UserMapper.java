package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:59
 */
@Repository
public interface UserMapper {

    User getUserById(Integer id);

    User getUserByUsername(String username);

    //获取同一寝室的人，不包含自己
    List<User> getSameDormitoryStudent(String dormitory, String username);

    void updatePassword(String username, String password);

    List<User> listUsers();

    void addUser(User user);

}
