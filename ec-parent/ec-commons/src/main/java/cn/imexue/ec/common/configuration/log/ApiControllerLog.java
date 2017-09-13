package cn.imexue.ec.common.configuration.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ApiControllerLog extends AbstractLog{

	private static final Logger log = LoggerFactory.getLogger("access");
	
//	@Pointcut("execution(* cn.imexue.ec.web.web.controller..*.*(..))")  
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void apiControllerLog() {  
    } 
	
	@Around("apiControllerLog()")
	public Object doAround(ProceedingJoinPoint pjd) throws Throwable {
		log.debug("access");
		return log(pjd, log);
	}

	@Override
	protected void errorExecute(Throwable e) {
		log.error("接口出错,信息:{}",e.getMessage());
	}
}
