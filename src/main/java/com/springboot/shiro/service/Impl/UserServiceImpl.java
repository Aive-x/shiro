package com.springboot.shiro.service.Impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.dto.UserDto;
import com.springboot.shiro.service.CourseService;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:58
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CourseService courseService;
    private static final String encryptSalt = "F12839WhsnnEV$#23b";

    @Override
    public User getUserById(Integer id){
        return userMapper.getUserById(id);
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
    public UserDto getUserInfo(String username) {
        User user = this.getUserByUsername(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        JwcAccount jwcAccount = courseService.getJwcAccount(userDto.getUsername());
        if (jwcAccount != null && jwcAccount.getBind() == 1){
            userDto.setSystem("已绑定");
        }
        else {
            userDto.setSystem("未绑定");
        }
        return userDto;
    }

    @Override
    public void updatePassword(String oldPass, String newPass, String checkPass, ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        User user = userMapper.getUserByUsername(JwtUtil.getUsername(httpRequest.getHeader("Authorization")));

        if(!getHashPassword(oldPass).equals(user.getPassword())){
            throw new MarsRuntimeException(ErrorCodeMessage.WRONG_PASSWORD);
        }
        if (!newPass.equals(checkPass)){
            throw new MarsRuntimeException(ErrorCodeMessage.WRONG_PASSWORD);
        }
        userMapper.updatePassword(user.getUsername(), getHashPassword(newPass));
    }

    @Override
    public List<User> listUsers() throws Exception {
        return userMapper.listUsers();
    }

    @Override
    public void addUser(User user) throws Exception {
        userMapper.addUser(user);
    }

    public String getHashPassword(String password){
        char[] charPassword = password.toCharArray();
        Object hashPassword = new SimpleHash("SHA-256", charPassword, ByteSource.Util.bytes(encryptSalt), 1);
        return hashPassword.toString();
    }
}
