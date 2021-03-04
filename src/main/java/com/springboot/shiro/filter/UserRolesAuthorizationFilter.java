package com.springboot.shiro.filter;

import com.springboot.shiro.dao.bean.RouteResource;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.RouteResourceService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xutianhong
 * @Date 2020/9/25 10:15
 */
public class UserRolesAuthorizationFilter extends AuthorizationFilter {

    @Autowired
    private RouteResourceService routeResourceService;

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mappedValue) throws Exception {

        if (isLoginRequest(servletRequest, servletResponse)){
            return true;
        }
        Subject subject = getSubject(servletRequest, servletResponse);
        String path = getPathWithinApplication(servletRequest);
        List<String> listRoles = routeResourceService.getRoleByPath(path);

        boolean[] booleans =  subject.hasRoles(listRoles);
        for (int i = 0; i < booleans.length; ++i){
            if (booleans[i]){
                return true;
            }
        }

        /*String[] rolesArray = (String[]) mappedValue;
        //没有角色限制，有权限访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }
        for (String role : rolesArray) {
            //若当前用户是rolesArray中的任何一个，则有权限访问
            if (subject.hasRole(role)){
                return true;
            }
        }*/
        return false;
    }

    /**
     * 权限校验失败，错误处理
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        return false;
    }

}
