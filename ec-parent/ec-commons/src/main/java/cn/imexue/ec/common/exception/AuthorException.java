package cn.imexue.ec.common.exception;

/**
 * 未登陆异常
 * @author Administrator
 *
 */
public class AuthorException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 152997603200431270L;

	public AuthorException(){}
	
	public AuthorException(String message){
		super(message);
	}
	
}
