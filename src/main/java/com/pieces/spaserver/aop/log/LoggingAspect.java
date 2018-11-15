package com.pieces.spaserver.aop.log;

import com.pieces.spaserver.model.user.User;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Xujing
 * @ClassName: ${CLASS_NAME}
 * @Description:利用aop实现对所有接口访问的日志记录
 * @PackageName: com.pieces.spaserver.aop
 * @date 13:48 2018/11/15
 * @编辑：
 * @描述：
 */
@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.pieces.spaserver.control..*(..))")
    public void pointCut(){
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        // 获取当前连接对象
        User user = getUserInfo();
        String userNo = null == user ? "null" : user.getNo() ;
        String userName = null == user ? "未登录用户" : user.getLoginName();
        // 获取请求request
        HttpServletRequest request = getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        logger.info("===>日志记录:用户访问接口  用户:{ 编号:" + user.getNo() + " , 用户名:" + user.getLoginName() + " } , 请求内容:{ url:" + url + " , method:" + method + " , ip:" + ip + " } , 接口方法:" + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(value = "pointCut()" , returning = "result")
    public void afterReturning(Object result){
        // 获取当前连接对象
        User user = getUserInfo();
        logger.info("===>日志记录:用户访问接口 成功  用户:{ 编号:" +user.getNo() + " , 用户名:" + user.getLoginName() + " } 获取数据" + result.toString() );
    }

    @AfterThrowing(value = "pointCut()", throwing = "exception")
    public void logThrown(Exception exception) {
        // 获取当前连接对象
        User user = getUserInfo();
        logger.info("===>日志记录:用户访问接口 出错 用户:{ 编号:" +user.getNo() + " , 用户名:" + user.getLoginName() + " }" + exception.getMessage());
    }

    @After("pointCut()")
    public void after(JoinPoint joinPoint){
        // 获取当前连接对象
        User user = getUserInfo();
        logger.info("===>日志记录:用户访问接口 结束 用户:{ 编号:" +user.getNo() + " , 用户名:" + user.getLoginName() + " } 访问结束 接口方法:" + joinPoint.getSignature().toShortString());
    }

    /**
     * 获取当前连接用户对象，即访问接口用户对象
     * @return
     */
    private User getUserInfo(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userInfo = new User();
        user.setNo(null == user ? "null" : user.getNo());
        user.setLoginName(null == user ? "未登录用户" : user.getLoginName());
        return user;
    }

    /**
     * 获取当前请求request对象
     * @return
     */
    private HttpServletRequest getRequest(){
        RequestAttributes requestAttribute = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttribute).getRequest();
        return request;
    }
}
