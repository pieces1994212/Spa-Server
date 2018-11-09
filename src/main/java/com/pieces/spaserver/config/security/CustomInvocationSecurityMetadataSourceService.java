package com.pieces.spaserver.config.security;

import com.pieces.spaserver.mapper.function.FunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 自定义对访问权限的判断，这里通过查库获得url资源所需的权限角色，返回此结果集合，暂时不启用缓存
 * @PackageName: com.pieces.spaserver.config.security
 * @date 10:13 2018/11/8
 * @编辑：
 * @描述：
 */
@Service
public class CustomInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    FunctionMapper functionMapper;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        String url = ((FilterInvocation) object).getRequestUrl();
        // 这里对静态资源进行过滤
        if (!url.matches("^.*\\.(js|png|css|font|jpg).*$")) {
            List<String> roles = functionMapper.queryRolesByUrl(url);
            for(String role:roles){
               ConfigAttribute ca = new SecurityConfig(role);
                atts.add(ca);
            }
        }
        return atts;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
