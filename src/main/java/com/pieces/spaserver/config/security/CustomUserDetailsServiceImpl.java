package com.pieces.spaserver.config.security;

import com.pieces.spaserver.mapper.user.UserMapper;
import com.pieces.spaserver.model.user.Role;
import com.pieces.spaserver.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 自定义
 * @PackageName: com.pieces.spaserver.config.security
 * @date 15:29 2018/11/7
 * @编辑：
 * @描述：
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByName(username);
        if(null != user){
            List<Role> roleList = userMapper.getUserRoles(user);
            if(roleList.size() == 0){
                return null;
            }
            user.setRoles(roleList);
        }
        return user;
    }

    /**
     * 锁定用户
     * @param user
     */
    public void lockUser(User user){
        userMapper.lockUser(user);
    }

    /**
     * 解锁用户
     * @param user
     */
    public void releaseUser(User user){
        userMapper.releaseUser(user);
    }

    /**
     * 更新用户登陆次数
     * @param user
     */
    public void updateUserLoginCount(User user){
        userMapper.changeLoginCount(user);
    }
}
