/**
 * 
 */
package org.powerSystem.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogUtil {

	// 日志类
	private static final Log logger = LogFactory.getLog(LogUtil.class);

	/**
	 * service 的方法错误时执行日志记录
	 */
	@AfterThrowing(pointcut = "execution(* org.powerSystem.service.*.* (..))", throwing = "e")
	public void error(JoinPoint jp, Throwable e) {
	}

	@Before("execution(* org.powerSystem.service.*.* (..))")
	public void before(JoinPoint jp) {
	}
}
