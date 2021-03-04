package com.springboot.shiro.dao;

import com.springboot.shiro.dao.bean.SystemMenu;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author xutianhong
 * @Date 2021/3/4 3:53 下午
 */
@Repository
public interface SystemMenuMapper {
    List<SystemMenu> listSystemMenu();
}
