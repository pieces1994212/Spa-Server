package com.pieces.spaserver.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 自定义授权比对方法，重写decide()比对当前用户所拥有权限角色与资源所需权限角色，若有则返回（授权通过），若无则抛出授权拒绝异常
 * @PackageName: com.pieces.spaserver.config.security
 * @date 15:01 2018/11/8
 * @编辑：
 * @描述：
 */
@Service
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null == configAttributes){
            return ;
        }
        for(ConfigAttribute ca:configAttributes){
            String needRole = ((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
                if(needRole.trim().equals(ga.getAuthority().trim())){
                    return ;
                }
            }
        }
        // 权限比对错误，授权失败
        throw new AccessDeniedException("");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
