package cn.imexue.ec.web.web.role;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.imexue.ec.common.exception.AuthorException;
import cn.imexue.ec.common.util.login.LoginInfo;
import cn.imexue.ec.common.util.login.LoginUtil;

/**
 * 权限验证相关类
 * @author hl
 *
 */
@Order(1)
@Aspect
@Component
public class RoleValidate {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)") 
    public void roleValid() {  
    } 
	
	@Before("roleValid()")
	public void introcepter(JoinPoint pjp) throws Throwable{  
		Signature signature = pjp.getSignature();    
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();
	    Role role = AnnotationUtils.findAnnotation(targetMethod, Role.class);
	    if(role == null){
	    	role = AnnotationUtils.findAnnotation(pjp.getTarget().getClass(),  Role.class);
	    }
	    if(role == null){
	    	return ;
	    }
	    LoginInfo loginInfo = LoginUtil.getLoginInfo();
	    if(loginInfo==null){
	    	log.debug("用户未登录");
	    	throw new AuthorException("用户未登陆");
	    }
	    String role2 = loginInfo.getUserRole();
	    
	    for(RoleType type: role.exclude()){
	    	if(type.name().equalsIgnoreCase(role2)){
	    		log.debug("用户{}无权限访问方法{}",loginInfo.getLoginId(),signature.getName());
	    		throw new AuthorException("无权限");
	    	}
	    }
	    
	    for(RoleType type: role.value()){
	    	if(type == RoleType.ALL||type.name().equalsIgnoreCase(role2))
	    		return ;
	    }
	    log.debug("用户{}无权限访问方法{}",loginInfo.getLoginId(),signature.getName());
	    throw new AuthorException("无权限");
	}
	
}
