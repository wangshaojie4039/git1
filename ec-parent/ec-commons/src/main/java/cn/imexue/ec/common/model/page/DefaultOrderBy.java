package cn.imexue.ec.common.model.page;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 默认排序
 * @author hl
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultOrderBy {

	String value() default "id";
	
	Page.OrderType orderType() default Page.OrderType.ASC;
}
