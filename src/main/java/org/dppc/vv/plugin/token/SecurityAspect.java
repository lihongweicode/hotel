package org.dppc.vv.plugin.token;

import cn.hutool.core.lang.Console;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dppc.vv.admin.entity.User;
import org.dppc.vv.common.annotation.NotTokenSecurity;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Token验证
 * @author Administrator
 *
 */
/*@Aspect
@Component*/
public class SecurityAspect {
	
	private static final String DEFAULT_TOKEN_NAME = "X-Token";

	@Resource(name="redisTokenManager")
	private TokenManager tokenManager;
	
//	@Pointcut("@annotation(org.springframework.web.bind.annotation.RestController)")
	@Pointcut("execution(public * org.dppc.vv.*.rest.*.*(..))")
	public void controllerAspect() {
		
	}
	
	/**
	 * 接收到客户端请求时执行
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerAspect()")
	public Object execute(ProceedingJoinPoint pjp) throws Throwable {
		Console.log("开始执行权限判断");
		// 从切点上获取目标方法
		MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
		Method method = methodSignature.getMethod();//获取被拦截的方法
//		String methodName = method.getName(); //获取被拦截的方法名
		/**
		 * 无需token权限，使用 @NotTokenSecurity
		 */
		if (method.isAnnotationPresent(NotTokenSecurity.class)) {
			// 调用目标方法
			return pjp.proceed();
		}

		/**
		 * 验证Token
		 */
		// 从 request header 中获取当前 token
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		String token = 	request.getHeader(DEFAULT_TOKEN_NAME);
		if(StringUtils.isEmpty(token)){
			throw new TokenException("客户端X-Token参数不能为空,且从Header中传入,如果没有登录,请先登录获取Token");
		}
		User u = tokenManager.getUserByToken(token);
		// 检查 token 有效性
		if (u == null) {
			String message = String.format("Token [%s] 非法", token);
			throw new TokenException(message);
		}
		// 调用目标方法
		return pjp.proceed();
	}
}
