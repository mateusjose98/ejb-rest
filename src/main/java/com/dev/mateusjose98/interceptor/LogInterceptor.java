package com.dev.mateusjose98.interceptor;

import java.util.logging.Logger;

import javax.annotation.Priority;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolationException;


@Interceptor
@Priority(1)
@com.dev.mateusjose98.interceptor.Logger
public class LogInterceptor {
	
	private static Logger logger = Logger.getLogger(LogInterceptor.class.getName());

	@AroundInvoke
	public Object handleException(InvocationContext context) throws Exception {
		logger.info("Caiu no interceptor!!!");
		try {
		
			return context.proceed();
		
		}catch (Exception e) {
			if( e.getCause() instanceof ConstraintViolationException) {
				logger.info(e.getMessage());
			} else {
				logger.severe(e.getMessage());
			}
			throw e;
			
		}
		
	}
	
}
