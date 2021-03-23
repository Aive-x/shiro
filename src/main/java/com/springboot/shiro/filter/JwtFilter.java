package com.springboot.shiro.filter;

import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.dao.bean.JwtToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author xutianhong
 * @Date 2020/9/23 16:27
 */
@Slf4j
public class JwtFilter extends AuthenticatingFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request, response)) {
            return true;
        }
        if (StringUtils.isEmpty(getAuthzHeader(request))) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (Exception e) {
            log.error("Error occurs when login", e);
            throw new MarsRuntimeException(ErrorCodeMessage.INVALID_TOKEN);
        }
        return allowed || super.isPermissive(mappedValue);
    }

    // 获取请求体内的token并存入对象
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String authorization = getAuthzHeader(request);
        JwtToken jwtToken = new JwtToken(authorization);
        if (jwtToken.getJwt() == null) {
            return null;
        }
        log.info("token:{}", jwtToken.getJwt());
        return jwtToken;
    }

    /**
     * 如果这个Filter在之前isAccessAllowed（）方法中返回false,则会进入这个方法。我们这里直接返回错误的response
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value());
        return false;
    }

    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader("Authorization");
    }

}
