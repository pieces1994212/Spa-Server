package com.pieces.spaserver.config;

import com.pieces.spaserver.config.security.CustomAuthenticationEntryPoint;
import com.pieces.spaserver.config.security.CustomAuthenticationFailHandler;
import com.pieces.spaserver.config.security.CustomAuthenticationProvider;
import com.pieces.spaserver.config.security.CustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: Security配置
 * @PackageName: com.pieces.spaserver.config
 * @date 8:55 2018/11/7
 * @编辑：
 * @描述：
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public CustomAuthenticationProvider authenticationProvider(){
        return new CustomAuthenticationProvider();
    }

    @Bean
    public HttpStatusReturningLogoutSuccessHandler LogoutSuccessHandler() {
        return new HttpStatusReturningLogoutSuccessHandler();
    }

    @Bean
    public CustomAuthenticationSuccessHandler loginSuccessHandler(){
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailHandler loginFailHandler(){
        return new CustomAuthenticationFailHandler();
    }

    @Bean
    public CustomAuthenticationEntryPoint authenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("rollrock")
                .passwordParameter("rockroll")
                .successHandler(loginSuccessHandler())
                .failureHandler(loginFailHandler())
                .and()
                .logout().logoutSuccessHandler(LogoutSuccessHandler())
                .and()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers("/login");
    }
}

