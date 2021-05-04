package com.springboot.shiro.service.Impl;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.dto.UserDto;
import com.springboot.shiro.service.CourseService;
import com.springboot.shiro.service.TeacherClassesService;
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
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private TeacherClassesService teacherClassesService;
    private static final String encryptSalt = "F12839WhsnnEV$#23b";

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public List<User> getSameDormitoryStudent(String studentNumber, String dormitory) {
        List<User> userList = userMapper.listUsers();
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        }
        userList = userList.stream().filter(user -> {
            if (user.getDormitory() != null && !user.getUsername().equals(studentNumber)
                && user.getDormitory().equals(dormitory)) {
                return true;
            }
            return false;
        }).collect(Collectors.toList());
        return userList;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userMapper.getUserByUsername(username);
        return user;
    }

    @Override
    public UserDto getUserInfo(String username) {
        User user = this.getUserByUsername(username);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        if ("student".equals(user.getRole())) {
            JwcAccount jwcAccount = courseService.getJwcAccount(userDto.getUsername());
            if (jwcAccount != null && jwcAccount.getBind() == 1) {
                userDto.setSystem("已绑定");
            } else {
                userDto.setSystem("未绑定");
            }
        } else {
            List<String> classesList = teacherClassesService.listTeacherClasses(username);
            StringBuilder stringBuilder = new StringBuilder();
            for (String classes : classesList) {
                stringBuilder.append(classes).append(" ");
            }
            userDto.setClasses(stringBuilder.toString());
        }
        return userDto;
    }

    @Override
    public void updatePassword(String oldPass, String newPass, String checkPass, ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        User user = userMapper.getUserByUsername(JwtUtil.getUsername(httpRequest.getHeader("Authorization")));

        if (!getHashPassword(oldPass).equals(user.getPassword())) {
            throw new MarsRuntimeException(ErrorCodeMessage.WRONG_PASSWORD);
        }
        if (!newPass.equals(checkPass)) {
            throw new MarsRuntimeException(ErrorCodeMessage.WRONG_PASSWORD);
        }
        userMapper.updatePassword(user.getUsername(), getHashPassword(newPass));
    }

    @Override
    public List<User> listUsers() throws Exception {
        return userMapper.listUsers();
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void addUser(List<String[]> userList) {
        userList.remove(0);
        userList.forEach(userString -> {
            User user = new User();
            user.setUsername(userString[0]);
            user.setCollege(userString[1]);
            user.setClasses(userString[2]);
            user.setName(userString[3]);
            user.setDormitory(userString[4]);
            user.setPassword(getHashPassword(userString[5]));
            user.setRole(userString[6]);
            user.setPhone(userString[7]);
            if (ObjectUtils.isEmpty(this.getUserByUsername(user.getUsername()))) {
                this.addUser(user);
            } else {
                this.updateUser(user);
            }
        });

    }

    public String getHashPassword(String password) {
        char[] charPassword = password.toCharArray();
        Object hashPassword = new SimpleHash("SHA-256", charPassword, ByteSource.Util.bytes(encryptSalt), 1);
        return hashPassword.toString();
    }
}
