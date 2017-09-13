package cn.imexue.ec.common.exception;

/**
 * ϵͳ�޷�������쳣
 * @author Li Jianfeng
 *
 */
public class AppUnChkException extends Exception {

	private static final long serialVersionUID = 5567111365902190842L;

	private String message;

	private String param;

	public AppUnChkException() {
		super();
	}

	public AppUnChkException(String message, String param) {
		super(message);
		this.message = message;
		this.param = param;
	}

	public AppUnChkException(String message) {
		super(message);
		this.message = message;
	}

	public AppUnChkException(String message, Throwable throwable) {
		super(message, throwable);
		this.message = message;
	}

	public AppUnChkException(Throwable throwable) {
		super(throwable);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
}
