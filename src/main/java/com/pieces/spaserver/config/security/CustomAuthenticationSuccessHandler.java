package com.pieces.spaserver.config.security;

import com.pieces.spaserver.model.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description: 自定义用户登陆成功后的处理，这里是返回成功的状态以及用户名
 * @PackageName: com.pieces.spaserver.config.security
 * @date 14:31 2018/11/7
 * @编辑：
 * @描述：
 */
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        logger.info("用户:"+user.getLoginName()+"登陆成功！");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write( "{\"status\":\"success\",\"username\":\""+user.getLoginName()+"\"}" );
        out.flush();
        out.close();
    }
}
