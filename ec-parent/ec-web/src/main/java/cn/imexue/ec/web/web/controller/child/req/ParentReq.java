package cn.imexue.ec.web.web.controller.child.req;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class ParentReq {

	@NotNull
	private Long childId;
	
	@NotNull
	@Pattern(regexp="^[1]{1}[2-9]{1}[0-9]{9}$",message="手机号码格式不正确")
	private String mobile;
	 
	@NotNull
	@Length(max=20)
	private String parentName;
	
	@NotNull
	@Pattern(regexp="(BB|MM|YY|NN|WG|WP|OTHER){1}")
	private String relationCode;

	@Length(max=4)
	private String relationName;
	
	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}
	
}
