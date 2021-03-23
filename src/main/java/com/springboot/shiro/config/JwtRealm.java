package com.springboot.shiro.config;

import com.springboot.shiro.common.ErrorCodeMessage;
import com.springboot.shiro.common.MarsRuntimeException;
import com.springboot.shiro.util.JwtUtil;
import com.springboot.shiro.dao.bean.JwtToken;
import com.springboot.shiro.dao.bean.User;
import com.springboot.shiro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xutianhong
 * @Date 2020/9/23 16:47
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /*
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     * */
    @Override
    public boolean supports(AuthenticationToken token) {
        //这个token就是从过滤器中传入的jwtToken
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    //认证
    //这个token就是从过滤器中传入的jwtToken
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        String token = (String) authenticationToken.getPrincipal();

        if(token == null){
            log.info("error");
            throw new AuthenticationException("token 无效！");
        }

        //判断
        //下面是验证这个user是否是真实存在的

        try {
            String username = JwtUtil.getUsername(token);
            User user =  userService.getUserByUsername(username);
            if (user == null) {
                throw new AuthenticationException("用户"+username+"不存在") ;
            }
        }catch (Exception e){
            throw new MarsRuntimeException(ErrorCodeMessage.INVALID_TOKEN);
        }
        return new SimpleAuthenticationInfo(token,token,getName());
        //这里返回的是类似账号密码的东西，但是jwtToken都是jwt字符串。还需要一个该Realm的类名

    }

}
