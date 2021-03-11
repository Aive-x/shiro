package com.springboot.shiro.service.Impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
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
    private static final String encryptSalt = "F12839WhsnnEV$#23b";

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

    @Override
    public void updatePassword(String username, String password) {
        char[] test = password.toCharArray();
        Object hashPassword = new SimpleHash("SHA-256", test, ByteSource.Util.bytes(encryptSalt), 1);
        userMapper.updatePassword(username, hashPassword.toString());
    }
}
