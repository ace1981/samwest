package com.hujao.component;


import java.util.Arrays;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
	private Logger logger = LoggerFactory.getLogger(getClass());

	ThreadLocal<Long> reststartTime = new ThreadLocal<>();
	ThreadLocal<Long> daostartTime = new ThreadLocal<>();
	ThreadLocal<Long> domainstartTime = new ThreadLocal<>();
	
	@Pointcut("execution(public * com.hujao.web..*.*(..))")
	public void restLog(){}
	@Around("restLog()")  
	public Object doRestAround(ProceedingJoinPoint pjp){  
		Object object=null;
		try {  
			object=pjp.proceed();  
		} catch (Throwable e) {  
			logger.error("rest服务异常:", e);
			e.printStackTrace();
			object="error";
		}   
		return object;
	}  
	@Before("restLog()")
	public void doRestBefore(JoinPoint joinPoint) throws Throwable {
		reststartTime.set(System.currentTimeMillis());
		logger.info("rest_start:" + System.currentTimeMillis());
		 
	}
	@AfterReturning(returning = "ret", pointcut = "restLog()")
	public void doRestAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("rest_end  :" + System.currentTimeMillis());
		logger.info("rest_spend: " + (System.currentTimeMillis() - reststartTime.get()));
	}
	
	@Pointcut("execution(public * com.hujao.service..*.*(..))")
	public void domainLog(){}
	@Around("domainLog()")  
	public Object doDomainAround(ProceedingJoinPoint pjp){  
		Object object=null;
		try {  
			object=pjp.proceed();  
		} catch (Throwable e) {  
			logger.error("domain服务异常:", e);
			e.printStackTrace();
			object="error";
		}   
		return object;
	}  
	@Before("domainLog()")
	public void doDomainBefore(JoinPoint joinPoint) throws Throwable {
		domainstartTime.set(System.currentTimeMillis());
		logger.info("domain_start:" + System.currentTimeMillis()); 
	}
	@AfterReturning(returning = "ret", pointcut = "domainLog()")
	public void doDomainAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("domain_end  :" + System.currentTimeMillis());
		logger.info("domain_spend: " + (System.currentTimeMillis() - domainstartTime.get()));
	}
	
	
	@Pointcut("execution(public * com.hujao.dao..*.*(..))")
	public void dataaccessLog(){}
	@Around("dataaccessLog()")  
	public Object doDataaccessAround(ProceedingJoinPoint pjp){  
		Object object=null;
		try {  
			object=pjp.proceed();  
		} catch (Throwable e) {  
			logger.error("dataaccess服务异常:", e);
			e.printStackTrace();
			object="error";
		}   
		return object;
	}  
	@Before("dataaccessLog()")
	public void doDataaccessBefore(JoinPoint joinPoint) throws Throwable {
		daostartTime.set(System.currentTimeMillis());
		logger.info("dataaccess_start:" + System.currentTimeMillis());
		 
	}
	@AfterReturning(returning = "ret", pointcut = "dataaccessLog()")
	public void doDataaccessAfterReturning(Object ret) throws Throwable {
		// 处理完请求，返回内容
		logger.info("dataaccess_end  :" + System.currentTimeMillis());
		logger.info("dataaccess_spend: " + (System.currentTimeMillis() - daostartTime.get()));
	}
	
	

}