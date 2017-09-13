package cn.imexue.ec.web.model.bo;
/**
 * 卡导入信息接收BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年2月28日
 * @author wangshaojie
 * @version 1.0
 */
public class ChildCardExcelBO {
	
	private String cardNo;
	
	private String className;
	
	private String childName;
	
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
	public String toString() {
		return "ChildCardExcelBO [cardNo=" + cardNo + ", className="
				+ className + ", childName=" + childName + ", idCardNo="
				+ idCardNo + "]";
	}


	
	
}
