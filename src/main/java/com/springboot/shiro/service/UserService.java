package com.springboot.shiro.service;

import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.dto.UserDto;

import javax.servlet.ServletRequest;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:58
 */
public interface UserService {

    User getUserById(Integer id);

    User getUserByUsername(String username);

    UserDto getUserInfo(String username);

    List<User> getSameDormitoryStudent(String studentNumber, String dormitory);

    void updatePassword(String oldPass, String newPass, String checkPass, ServletRequest request);

    List<User> listUsers() throws Exception;

    void addUser(User user) throws Exception;

}
