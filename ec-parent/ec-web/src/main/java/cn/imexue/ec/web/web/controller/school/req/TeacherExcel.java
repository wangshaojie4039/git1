package cn.imexue.ec.web.web.controller.school.req;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.web.web.excel.ExcelException;
import cn.imexue.ec.web.web.excel.ExcelImportProperty;


public class TeacherExcel{

	
	@ExcelImportProperty(columnName = "姓名")
	@Length(min=1,max=50,message="姓名不能超过50个字")
	private String name;
	
	@ExcelImportProperty(columnName = "手机号码")
	@Pattern(regexp="^[1]{1}[2-9]{1}[0-9]{9}$",message="手机号码格式不正确")
	private String mobile;
	
	@ExcelImportProperty(columnName = "学校角色")
	@Length(min=2,max=2,message="学校角色不正确")
	private String schoolRole;
	
	@ExcelImportProperty(columnName = "班级名称",nullable=true)
	@Length(min=1,max=50,message="班级名称不能大于50位")
	private String className;
	
	@ExcelImportProperty(columnName = "是否为班主任",nullable=true)
	@Pattern(regexp="(是|否){1}",message="是否为班主任不正确")
	private String isMaster;
	
	@ExcelImportProperty(columnName = "性别",nullable=true)
	@Length(min=1,max=1,message="性别不正确")
	private String sex;
	
	@ExcelImportProperty(columnName = "生日",nullable=true)
	@Length(min=8,max=10,message="生日格式不正确")
	private String birthday;

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

	public String getSchoolRole() {
		return schoolRole;
	}

	public void setSchoolRole(String schoolRole) {
		this.schoolRole = schoolRole;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getIsMaster() {
		return isMaster;
	}

	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getSchoolRoleValue(){
		return "园长".equals(schoolRole)?"D":"T";
	}
	
	public Byte getIsMasterByte(){
		if(isMaster == null){
			return 0;
		}
		return (byte) ("是".equals(isMaster)?1:0);
	}
	
	public Byte getSexValue(){
		if("男".equals(sex))
			return 1;
		if("女".equals(sex))
			return 2;
		
		return 2;
	}
	
	public Date getBirthdayDate(){
		if(!StringUtil.isBlank(birthday)){
			Date date = DateUtil.formatStringToDay(birthday, "yyyy-MM-dd");
			if(date==null){
				throw new ExcelException("excel.date.error", birthday,"1999-01-01");
			}
			return date;
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println("19875641111".matches("^[1]{1}[2-9]{1}[0-9]{9}$"));
		
	}
	
}
