package com.pieces.spaserver.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:自定义用户登陆失败后的处理，这里是返回失败原因
 * @PackageName: com.pieces.spaserver.config.security
 * @date 14:37 2018/11/7
 * @编辑：
 * @描述：
 */
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

    Logger logger = LoggerFactory.getLogger(CustomAuthenticationFailHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("尝试登陆失败!错误原因"+exception.getMessage());
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write( "{\"status\":\"failed\",\"message\":\""+exception.getMessage()+"\"}" );
        out.flush();
        out.close();
    }
}
