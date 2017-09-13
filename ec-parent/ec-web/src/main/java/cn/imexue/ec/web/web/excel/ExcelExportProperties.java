package cn.imexue.ec.web.web.excel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface ExcelExportProperties {

	String columnName();
	
	int order();

	String dateFormat() default "";
}
