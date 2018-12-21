package com.pieces.spaserver.config.security;

import com.pieces.spaserver.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.security.auth.login.AccountExpiredException;
import java.util.Date;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:自定义用户登录验证
 * @PackageName: com.pieces.spaserver.config.security
 * @date 16:02 2018/11/7
 * @编辑：
 * @描述：
 */
public class CustomAuthenticationProvider implements AuthenticationProvider {

    Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    CustomUserDetailsServiceImpl userDetailsService;

    @Value("${user.lock.time}")
    private Integer lockingTime;

    @Value("${user.max.logincount}")
    private Integer maxLoginCount;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        String username = token.getName();
        UserDetails userDetails = null;
        User user = null;
        if(null != username){
            userDetails = userDetailsService.loadUserByUsername(username);
            user = (User) userDetails;
        }else{
            throw new BadCredentialsException("请输入用户名!");
        }
        if (userDetails == null) {
            throw new UsernameNotFoundException("用户名或密码错误！");
        } else if (!userDetails.isEnabled()) {
            throw new DisabledException("用户已被禁用！");
        } else if (!userDetails.isAccountNonExpired()) {
            try {
                throw new AccountExpiredException("账号已过期！");
            } catch (AccountExpiredException e) {
                logger.error("异常", e);
            }
        } else if (!userDetails.isAccountNonLocked()) {
            //判断是否过了锁定时间
            Date relaseDate =new Date(user.getLockTime().getTime() + lockingTime);
            if(System.currentTimeMillis()<relaseDate.getTime()){
                //还未到锁定时间,计算解除锁定时间
                Long mintues = (relaseDate.getTime()- System.currentTimeMillis())/(1000*60);
                throw new LockedException("此账号已锁定！请于"+mintues+"分钟后重试！");
            }
        } else if (!userDetails.isCredentialsNonExpired()) {
            throw new LockedException("凭证已过期！");
        }
        // 数据库用户的密码
        String password = userDetails.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 与authentication里面的credentials相比较
        String pwd = (String) token.getCredentials();
        if (!bCryptPasswordEncoder.matches(pwd, password)) {
            if(null == user.getLoginCount() || user.getLoginCount()<=maxLoginCount-2){
                //小于n-2次增加登陆次数，不锁定
                if(null == user.getLoginCount()){
                    user.setLoginCount(1);
                }else{
                    user.setLoginCount(user.getLoginCount()+1);
                }
                userDetailsService.updateUserLoginCount(user);
                throw new BadCredentialsException("用户名或密码错误！还有"+(maxLoginCount-user.getLoginCount())+"次机会！");
            }else{
                //最后一次尝试(n-1次)失败，锁定账户
                //或者已经N次，解锁后仍输入错误，继续锁定账户
                user.setLoginCount(maxLoginCount);
                user.setLockFlag(1);
                user.setLockTime(new Date());
                user.setLockReason("连续登陆失败超过最大次数");
                //更新登陆次数
                userDetailsService.updateUserLoginCount(user);
                //锁定用户
                userDetailsService.lockUser(user);
                logger.warn("账号锁定===>"+username+" 锁定原因:连续登陆失败超过最大次数");
                throw new BadCredentialsException("此账号已锁定!请于"+lockingTime/60000+"分钟后重试！");
            }
        }
        //登陆成功解除锁定
        userDetailsService.releaseUser(user);
        // 授权
        return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
