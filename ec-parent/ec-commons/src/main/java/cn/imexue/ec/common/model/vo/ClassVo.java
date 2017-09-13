package cn.imexue.ec.common.model.vo;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.ibatis.type.Alias;

@Alias("classvo")
public class ClassVo extends cn.imexue.ec.common.model.Class{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6493626600281913975L;

	private Integer childNum;
	
	private Integer parentNum;
	
	private TeacherVo master;
	
	private List<TeacherVo> teachers;
	
	private Byte isMaster;
	
	public TeacherVo getMaster() {
		return master;
	}

	public void setMaster(TeacherVo master) {
		this.master = master;
	}

	public List<TeacherVo> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<TeacherVo> teachers) {
		this.teachers = teachers;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public Integer getParentNum() {
		return parentNum;
	}

	public void setParentNum(Integer parentNum) {
		this.parentNum = parentNum;
	}
	
	public Byte getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(Byte isMaster) {
		this.isMaster = isMaster;
	}

	public String getTeachersName(){
		if(teachers == null||teachers.size()<1) return "";
		StringBuffer sb = new StringBuffer();
		teachers.forEach(x-> sb.append(x.getName()).append(","));
		return sb.substring(0, sb.length()-1).toString();
	}
	
	public List<Long> getTeacherIds(){
		if(teachers == null )
			return null;
		return  teachers.stream().map((x)->x.getId()).collect(Collectors.toList());
	}
	
	public String getMasterName(){
		if(master==null)return null;
		
		return master.getName();
	}
}
