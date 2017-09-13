package cn.imexue.ec.common.model.bo;
/**
 * 老师学校班级关系BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月27日
 * @author wangshaojie
 * @version 1.0
 */
public class TeacherSchoolClassMapBO {
	
	private Long teacherId;
	
	
	private String schoolRole;
	
	private String schoolName;
	
	private String className;
	
	private Byte isMaster;

	public Long getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Long teacherId) {
		this.teacherId = teacherId;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Byte getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Byte isMaster) {
		this.isMaster = isMaster;
	}

	public String getSchoolRole() {
		return schoolRole;
	}

	public void setSchoolRole(String schoolRole) {
		this.schoolRole = schoolRole;
	}

	@Override
	public String toString() {
		return "TeacherSchoolClassMapBO [teacherId=" + teacherId
				+ ", schoolRole=" + schoolRole + ", schoolName=" + schoolName
				+ ", className=" + className + ", isMaster=" + isMaster + "]";
	}


	
	
	
}
