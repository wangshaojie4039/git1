package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Parent extends DataEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1336122301561670086L;
    

	private String mobile;
	
	@JsonIgnore
	private String password;
	
	private String uid;
	
	private String uuid;
	
	private String name;
	
	private Byte sex;
	
	private Date birthday;
	
	private String logoUrl;
	
	private Byte isActive;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	
}