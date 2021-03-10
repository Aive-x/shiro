package com.springboot.shiro.service.Impl;

import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id){

        User user = userMapper.getUserById(id);

        return user;
    }

    @Override
    public List<User> getSameDormitoryStudent(String studentNumber, String dormitory) {
        return userMapper.getSameDormitoryStudent(dormitory, studentNumber);
    }

    @Override
    public User getUserByUsername(String username){
        User user = userMapper.getUserByUsername(username);
        return user;
    }

}
