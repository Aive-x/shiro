package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author xutianhong
 * @Date 2020/9/24 9:59
 */
@Repository
public interface UserMapper {

    User getUserById(Integer id);

    User getUserByUsername(String username);

}
