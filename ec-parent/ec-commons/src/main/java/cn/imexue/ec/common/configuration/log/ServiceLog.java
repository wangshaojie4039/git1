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
public class ServiceLog extends AbstractLog{

	private static final Logger log = LoggerFactory.getLogger("service");
	
	@Pointcut("bean(*ServiceImpl)")  
    public void serviceLog() {  
    } 
	
	@Around("serviceLog()")
	public Object doAround(ProceedingJoinPoint pjd) throws Throwable {
		return log(pjd, log);
	}

	@Override
	protected void errorExecute(Throwable e) {
		log.error("接口出错,信息:{}",e.getMessage());
	}
}
