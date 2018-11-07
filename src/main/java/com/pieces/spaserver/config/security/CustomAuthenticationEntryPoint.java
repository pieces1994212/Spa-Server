package com.pieces.spaserver.config.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:自定义用户未登录时访问后台接口的处理，返回未登陆错误
 * @PackageName: com.pieces.spaserver.config.security
 * @date 14:37 2018/11/7
 * @编辑：
 * @描述：
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED,authException.getMessage());
	}

	 public static boolean isAjaxRequest(HttpServletRequest request) {
		 String ajaxFlag = request.getHeader("X-Requested-With");
	     return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
	 }
}
