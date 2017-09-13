package cn.imexue.ec.web.web.controller.child.req;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.web.web.excel.ExcelException;
import cn.imexue.ec.web.web.excel.ExcelImportProperty;
import cn.imexue.ec.web.web.excel.ExcelValidate;

public class ChildExcel implements ExcelValidate{

	@ExcelImportProperty(columnName = "幼儿姓名")
	@Length(max=50,message="幼儿姓名不能超过50个字")
	private String name;
	
	@ExcelImportProperty(columnName = "性别")
	private String sex;
	
	@ExcelImportProperty(columnName = "生日",nullable=true)
	@Length(min=8,max=10,message="生日格式不正确")
	private String birthday;
	
	@ExcelImportProperty(columnName = "身份证号码",nullable=true)
	@Pattern(regexp="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$",message="身份证号码长度必须为18位")
	private String cardNo;
	
	@ExcelImportProperty(columnName = "班级名称")
	@Length(min=1,max=50,message="班级名称不能大于50位")
	private String className;
	
	@ExcelImportProperty(columnName = "爸爸姓名",nullable=true)
	@Length(max=50,message="爸爸姓名必须小于50字")
	private String fatherName;
	
	@ExcelImportProperty(columnName = "爸爸手机",nullable=true)
	@Pattern(regexp="^[1]{1}[2-9]{1}[0-9]{9}$",message="爸爸手机号码格式不正确")
	private String fatherMobile;
	
	@ExcelImportProperty(columnName = "妈妈姓名",nullable=true)
	@Length(max=50,message="妈妈姓名必须小于50字")
	private String motherName;
	
	@ExcelImportProperty(columnName = "妈妈手机",nullable=true)
	@Pattern(regexp="^[1]{1}[2-9]{1}[0-9]{9}$",message="妈妈手机号码格式不正确")
	private String motherMobile;
	
	@ExcelImportProperty(columnName = "考勤卡号1",nullable=true)
	@Pattern(regexp="\\d{10}",message="考勤卡号1长度必须为10位数字")
	private String cardNo1;
	
	@ExcelImportProperty(columnName = "考勤卡号2",nullable=true)
	@Pattern(regexp="\\d{10}",message="考勤卡号2长度必须为10位数字")
	private String cardNo2;	

	@ExcelImportProperty(columnName = "考勤卡号3",nullable=true)
	@Pattern(regexp="\\d{10}",message="考勤卡号3长度必须为10位数字")
	private String cardNo3;
	
	@ExcelImportProperty(columnName = "考勤卡号4",nullable=true)
	@Pattern(regexp="\\d{10}",message="考勤卡号4长度必须为10位数字")
	private String cardNo4;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getFatherMobile() {
		return fatherMobile;
	}

	public void setFatherMobile(String fatherMobile) {
		this.fatherMobile = fatherMobile;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getMotherMobile() {
		return motherMobile;
	}

	public void setMotherMobile(String motherMobile) {
		this.motherMobile = motherMobile;
	}

	public String getCardNo1() {
		return cardNo1;
	}

	public void setCardNo1(String cardNo1) {
		this.cardNo1 = cardNo1;
	}

	public String getCardNo2() {
		return cardNo2;
	}

	public void setCardNo2(String cardNo2) {
		this.cardNo2 = cardNo2;
	}

	public String getCardNo3() {
		return cardNo3;
	}

	public void setCardNo3(String cardNo3) {
		this.cardNo3 = cardNo3;
	}

	public String getCardNo4() {
		return cardNo4;
	}

	public void setCardNo4(String cardNo4) {
		this.cardNo4 = cardNo4;
	}

	@Override
	public void validate() {
		if(!StringUtil.isBlank(fatherName)&&StringUtil.isBlank(fatherMobile)){
			throw new ExcelException("excel.child.nameAndMobile.null", "父亲");
		}
		if(!StringUtil.isBlank(motherName)&&StringUtil.isBlank(motherMobile)){
			throw new ExcelException("excel.child.nameAndMobile.null", "母亲");
		}
		
	}
	
}
