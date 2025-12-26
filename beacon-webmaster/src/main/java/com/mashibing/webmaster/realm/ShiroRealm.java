package com.mashibing.webmaster.realm;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
public class ShiroRealm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1、基于token拿到用户名
        String username = (String) token.getPrincipal();
        //2、基于用户名获取用户信息(模拟数据库操作)
        if (StringUtils.isEmpty(username) || !"admin".equals(username)) {
            //3、查询完毕后，查看用户是否为null，为null就直接返回即可
            return null;
        }
        String password = "b39dc5da02d002e6ac581e5bb929d2e5";
        String salt = "09a8424ed5bf4373af6530fec2b29c0f";
        //4、不为null，说明用户名正确，封装AuthenticationInfo返回即可,设置密码加密方式和信息
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("用户信息", password, "shiroRealm");
        info.setCredentialsSalt(ByteSource.Util.bytes(salt));
        return info;
    }
}
