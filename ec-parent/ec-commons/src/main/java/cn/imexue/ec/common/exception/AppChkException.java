package cn.imexue.ec.common.exception;

/**
 * 系统需要处理的异常
 * 
 * @author Li Jianfeng
 */
public class AppChkException extends RuntimeException {

	private static final long serialVersionUID = 5567111365902190842L;

	private int code;
	
	private String message;

	private Object[] param;
	
	public AppChkException(int code, String message,Object... params) {
		super(message);
		this.code = code;
		this.message = message;
		this.param = params;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getParam() {
		return param;
	}

	public void setParam(Object[] param) {
		this.param = param;
	}

}
