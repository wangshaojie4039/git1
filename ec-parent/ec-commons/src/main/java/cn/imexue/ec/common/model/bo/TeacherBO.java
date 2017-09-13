package cn.imexue.ec.common.model.bo;
/**
 * 教师BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月27日
 * @author wangshaojie
 * @version 1.0
 */
public class TeacherBO {

	private Long id;
	
	private String name;
	
	private String mobile;
	
	private Byte sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "TeacherBO [id=" + id + ", name=" + name + ", mobile=" + mobile
				+ ", sex=" + sex + "]";
	}
	
	
	
}
