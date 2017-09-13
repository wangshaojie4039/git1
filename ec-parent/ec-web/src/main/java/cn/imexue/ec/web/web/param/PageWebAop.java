package cn.imexue.ec.web.web.param;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.page.Page.OrderType;
import cn.imexue.ec.common.model.page.PageQuery;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.login.LoginUtil;

@Order(2)
@Aspect
@Component
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageWebAop {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void requestMapping() {  
    } 

	
	@Before(value="requestMapping()&& args(page,..)")
    public void before(JoinPoint jp,Page page) {
		page.getSearch().put("schoolId", LoginUtil.getSchoolId());
		Signature signature = jp.getSignature();    
		MethodSignature methodSignature = (MethodSignature)signature;    
		String[] parameters =methodSignature.getParameterNames();
		Object[] args = jp.getArgs();
		for(int i =0; i<args.length;i++){
			Object o = args[i];
			if(o == null ||ClassUtils.isAssignable(o.getClass(), page.getClass()))
				//为null或者page
				continue;
			if( o instanceof PageQuery){
				PageQuery query = ((PageQuery)o);
				page.getSearch().putAll(query.getSearch());
				page.setPageNo(query.getPageNo());
				page.setPageSize(query.getPageSize());
				try {
					if(!StringUtil.isBlank(query.getOrderBy())){
						page.setOrderBy(query.getOrderBy());
					}else{
						//默认按id倒叙
						page.setOrderBy("id");
					}
				} catch (Exception e) {}
				if(!StringUtil.isBlank(query.getOrderType())){
					page.setOrderType(Page.OrderType.valueOf(query.getOrderType()));
				}else{
					//默认按id倒叙
					page.setOrderType(OrderType.DESC);
				}
			}
			
			if(validateClass(o)){
				page.getSearch().put(parameters[i], o);
			}else if(o instanceof String){
				//是String
				String param = (String) o;
				if(!StringUtil.isEmpty(param.trim())){
					page.getSearch().put(parameters[i], param.trim());
					args[i] = param.trim();
				}else{
					args[i] = null;
					continue;
				}
			}
			//不是基础类型，不做处理
		}
		
		log.debug("search={}",page.getSearch());
    }
	
	private boolean validateClass(Object object){
		Class clazz = object.getClass();
		if(ArrayUtils.contains(classes, clazz))
			return true;
		return false;
	}
	
	private static final Class[] classes = {Long.class,Integer.class,Short.class,
		Byte.class,Double.class,Float.class,BigDecimal.class,Boolean.class,Character.class
		,Date.class};
	
}
