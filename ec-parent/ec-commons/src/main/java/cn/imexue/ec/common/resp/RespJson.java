package cn.imexue.ec.common.resp;

import java.io.Serializable;

public class RespJson implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2734178085592311840L;

	private Integer result;
	
	private Integer code;
	
	private String msg;
	
	private Object data;

	public Integer getResult() {
		return result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
