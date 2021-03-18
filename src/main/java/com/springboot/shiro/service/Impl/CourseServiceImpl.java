package com.springboot.shiro.service.Impl;

import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.JwcAccountMapper;
import com.springboot.shiro.dao.UserMapper;
import com.springboot.shiro.dao.bean.JwcAccount;
import com.springboot.shiro.service.CourseService;
import com.springboot.shiro.service.bean.Course;
import com.springboot.shiro.util.HttpClientUtil;
import com.springboot.shiro.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xutianhong
 * @Date 2021/3/5 5:39 下午
 */
@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private static final String ZF_URL = "47.110.160.185:8060/api";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwcAccountMapper jwcAccountMapper;

    @Override
    public boolean jwcBind(JwcAccount jwcAccount) throws Exception {
        doJwcLogin(jwcAccount);
        jwcAccount.setBind(0);
        jwcAccountMapper.bindJwcAccount(jwcAccount);
        return true;
    }

    @Override
    public JwcAccount getJwcAccount(String studentNumber) {
        return jwcAccountMapper.getJwcAccount(studentNumber);
    }

    @Override
    public boolean doJwcLogin(JwcAccount jwcAccount) throws Exception {
        if (!jwcLogin(jwcAccount)) {
            JwcAccount existJwcAccount = this.getJwcAccount(jwcAccount.getStudentNumber());
            if (!ObjectUtils.isEmpty(existJwcAccount) && existJwcAccount.getBind() == 1){
                existJwcAccount.setBind(0);
                jwcAccountMapper.bindJwcAccount(existJwcAccount);
            }
            throw new MarsRuntimeException(ErrorCodeMessage.JWC_LOGIN_FAILED);
        }
        return true;
    }

    public boolean jwcLogin(JwcAccount jwcAccount) throws Exception {
        // User user = userMapper.getUserByUsername(jwcAccount.getStudentNumber());
        String url =
            "/login" + "?userNumber=" + jwcAccount.getJwcUsername() + "&userPassword=" + jwcAccount.getJwcPassword();
        Map<String, Object> result = HttpClientUtil.httpPostClient(ZF_URL + url, null, null, null);
        if ("登录成功".equals(result.get("body").toString())) {
            log.info("登录成功");
            return true;
        }
        return false;
    }

    @Override
    public List<Course> listCourse(JwcAccount jwcAccount) throws Exception {
        doJwcLogin(jwcAccount);
        String url = "/DefaultCourseList" + "?userNumber=" + jwcAccount.getJwcUsername() + "&userPassword="
            + jwcAccount.getJwcPassword();
        Map<String, Object> result = HttpClientUtil.httpGetClient(ZF_URL + url, null, null);

        jwcAccount.setBind(1);
        jwcAccountMapper.bindJwcAccount(jwcAccount);
        return JsonUtil.jsonToList(result.get("body").toString(), Course.class);
    }


}
