package cn.imexue.ec.common.exception;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.MyBatisSystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;

@RestControllerAdvice
public class ExceptionResolver {
	private static final Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

	@Resource
	private MessageSource messageSource;
	
	/**
	 * 登陆和权限验证异常处理
	 * @return
	 */
	@ExceptionHandler(AuthorException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public RespJson authException(){
		String message = messageSource.getMessage("app.unauth.err", null, Locale.getDefault());
		return RespJsonFactory.buildFailure(message, 1001);
	}
	
	@ExceptionHandler(ExcelAppException.class)
	@ResponseStatus(HttpStatus.OK)
	public RespJson excelException(ExcelAppException e){
		String message = messageSource.getMessage(e.getMessage(), e.getParam(), Locale.getDefault());
		String message2 = messageSource.getMessage("excel.error",new Object[]{message,e.getName()},Locale.getDefault());
		log.debug(message2);
		int code = e.getCode();
		return RespJsonFactory.buildFailure(message2,code);
	}
	
	
	/**
	 * 应用逻辑异常处理
	 * @see SysCodeController
	 * @param e{code:错误码,message:错误编号}
	 * @return
	 */
	@ExceptionHandler(AppChkException.class)
	@ResponseStatus(HttpStatus.OK)
	public RespJson appException(AppChkException e){
		log.debug(e.getMessage());
		String message = messageSource.getMessage(e.getMessage(), e.getParam(), Locale.getDefault());
		int code = e.getCode();
		return RespJsonFactory.buildFailure(message,code);
	}
	
	@ExceptionHandler(MyBatisSystemException.class)
	public RespJson myBatisSystemException(HttpServletRequest request,
			HttpServletResponse response,MyBatisSystemException e){
		Throwable cause = e.getCause().getCause();
		if(cause instanceof AppChkException){
			response.setStatus(HttpStatus.OK.value());
			return appException((AppChkException)cause);
		}else{
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return resolveException(request, response, e);
		}
		
	}
	
	/**
	 * web传参json解析异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus
	public RespJson notReadableException(HttpMessageNotReadableException e){
		log.debug(e.getMessage(),e);
		String message = messageSource.getMessage("system.web.json.err", null, Locale.getDefault());
		return RespJsonFactory.buildFailure(message,1003);
	}
	
	/**
	 * 参数验证异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus
	public RespJson validException(MethodArgumentNotValidException e){
		String message = e.getBindingResult().getFieldError().getDefaultMessage();
		log.debug(message);
		return RespJsonFactory.buildFailure(message,1002);
	}
	
	/**
	 * 参数绑定异常
	 * @param e
	 * @return
	 */
	@ExceptionHandler(BindException.class)
	@ResponseStatus
	public RespJson bindException(BindException e){
		String field = e.getFieldError().getField();
		Object value = e.getFieldError().getRejectedValue();
		String message = messageSource.getMessage("system.web.param.bind.error", new Object[]{field,value}, Locale.getDefault());
		log.debug(message);
		return RespJsonFactory.buildFailure(message,1009);
	}
	
	/**
	 * 系统异常
	 * @param request
	 * @param response
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(Throwable.class)
	@ResponseStatus
	public RespJson resolveException(HttpServletRequest request,
			HttpServletResponse response, Throwable ex) {
		log.error("系统错误",ex);
		String message = messageSource.getMessage("system.err", null, Locale.getDefault());
		return RespJsonFactory.buildFailure(message,1000);
	}
}
