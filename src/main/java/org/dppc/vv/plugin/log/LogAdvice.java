package org.dppc.vv.plugin.log;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dppc.vv.common.annotation.Log;
import org.springframework.stereotype.Component;

/**
 * 正常业务日志记录
 * @author zhumh
 *
 */
@Aspect
@Component
public class LogAdvice {
	
	public static final Logger logger = Logger.getLogger(LogAdvice.class);
	
	@Pointcut("@annotation(org.dppc.vv.common.annotation.Log)")
	public void controllerAspect() {
		
	}
	/**
	 * 当方法正常返回是执行
	 * @param joinPoint
	 */
	@AfterReturning("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		Method method = methodSignature.getMethod();
		Log log =  method.getAnnotation(Log.class);
		if(log != null){
			logger.info(String.format("记录业务日志  : [%s]",log.value()));
		}
	}
}
