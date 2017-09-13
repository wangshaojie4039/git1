package cn.imexue.ec.common.model.bo;
/**
 * 幼儿BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月27日
 * @author wangshaojie
 * @version 1.0
 */
public class ChildBO {

	private Long id;
	
	private String name;
	
	private String className;
	
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


	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "ChildBO [id=" + id + ", name=" + name + ", className="
				+ className + ", sex=" + sex + "]";
	}




	
}
