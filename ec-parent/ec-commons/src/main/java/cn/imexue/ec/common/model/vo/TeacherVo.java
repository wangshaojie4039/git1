package cn.imexue.ec.common.model.vo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.Teacher;

import com.fasterxml.jackson.annotation.JsonGetter;

@Alias("TeacherVo")
public class TeacherVo extends Teacher{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1742217447007072669L;

	private String role;//老师还是园长 T or D

	private Long schoolId;
	
	private List<ClassVo> classes;
	
	private String className;
	
	private Byte isMaster;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}

	public List<ClassVo> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassVo> classes) {
		this.classes = classes;
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

	@Override
	@JsonGetter
	public Date getCreateTime() {
		return super.getCreateTime();
	}
	
}
