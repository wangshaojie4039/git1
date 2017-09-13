package cn.imexue.ec.common.model.bo;


/**
 * 家长与幼儿关系BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月26日
 * @author wangshaojie
 * @version 1.0
 */
public class ParentChildMapBO {
	
	private  Long parentId;
	
	private String schoolName;
	
	private String className; 
	
	private String childName;
	
	private String relationName;

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String toString() {
		return "ParentChildMapBO [parentId=" + parentId + ", schoolName="
				+ schoolName + ", className=" + className + ", childName="
				+ childName + ", relationName=" + relationName + "]";
	}


	
	
}
