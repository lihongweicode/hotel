package org.dppc.vv.interceptor;

import org.apache.log4j.Logger;
import org.dppc.vv.admin.entity.User;
import org.dppc.vv.admin.service.AuthService;
import org.dppc.vv.common.annotation.NotTokenSecurity;
import org.dppc.vv.common.help.JsonHelp;
import org.dppc.vv.common.model.ResultDTO;
import org.dppc.vv.plugin.log.LogAdvice;
import org.dppc.vv.plugin.token.TokenException;
import org.dppc.vv.plugin.token.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @描述 :  权限拦截器
 * @作者 :	zhumh
 * @日期 :	2018/12/13
 * @时间 :	11:03
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    public static final Logger logger = Logger.getLogger(LogAdvice.class);

    private static final String DEFAULT_TOKEN_NAME = "X-Token";

    @Autowired
    private AuthService authService;

    @Resource(name = "redisTokenManager")
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("请求：" + request.getRequestURI());
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 判断接口是否需要登录
        NotTokenSecurity methodAnnotation = method.getAnnotation(NotTokenSecurity.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            return true;
        }
        String token = request.getHeader(DEFAULT_TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            throw new TokenException("客户端X-Token参数不能为空,且从Header中传入,如果没有登录,请先登录获取Token");
        }
        User u = tokenManager.getUserByToken(token);
        // 检查 token 有效性
        if (u == null) {
            returnJson(response, JsonHelp.toJSon(ResultDTO.tokenFail("权限认证失败，请重新登录！")));
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //controller 请求处理之后执行
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //整个请求结束后执行
    }


    private void returnJson(HttpServletResponse response, Object json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }

}
