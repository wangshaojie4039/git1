package cn.imexue.ec.web.web.controller.child.req;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.web.web.excel.ExcelException;
import cn.imexue.ec.web.web.excel.ExcelImportProperty;
import cn.imexue.ec.web.web.excel.ExcelValidate;

public class ChildCardExcel implements ExcelValidate   {
	
	@NotBlank
	@ExcelImportProperty(columnName = "考勤卡号")
	@Pattern(regexp="\\d{10}",message="考勤卡号长度必须为10位数字")
	private String cardNo;
	
	@NotBlank
	@ExcelImportProperty(columnName = "班级名称")
	@Length(min=1,max=50,message="班级名称不能大于50位")
	private String className;
	
	@NotBlank
	@ExcelImportProperty(columnName = "幼儿姓名")
	@Length(min=1,max=50,message="幼儿姓名不能超过50个字")
	private String childName;
	
	@ExcelImportProperty(columnName = "幼儿身份证号码",nullable=true)
	@Pattern(regexp="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$",message="身份证号码长度必须为18位")
	private String idCardNo;
	

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




	public String getChildName() {
		return childName;
	}


	public void setChildName(String childName) {
		this.childName = childName;
	}



	public String getIdCardNo() {
		return idCardNo;
	}



	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}



	@Override
	public void validate() {
		if(StringUtil.isNotEmpty(idCardNo)&&!StringUtil.validateIdCardNo(idCardNo)){
			throw new ExcelException("excel.childCard.notCorrectIdCardNo", idCardNo);
		}
	}



}
