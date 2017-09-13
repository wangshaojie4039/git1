package cn.imexue.ec.web.web.controller.sys.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;

import java.io.Serializable;

@ApiModel
public class LoginReq implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2322433703384186904L;

	@ApiParam(examples=@Example(@ExampleProperty(value="13600000001")))
	private String username;
	
	private String password;
	
	private String code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
