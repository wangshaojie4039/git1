package cn.imexue.ec.web.web.controller.device.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.device.req.DeviceVo.java<br/>
 * 初始作者： 崔业新<br/>
 * 创建日期： 2017年6月15日<br/>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class PointCardReq {

	private String	cardNo;
	private String	userType;
	@NotNull
	private Long	userId;
	private String	holderRoleCode;
	private Integer	versionNo;
	@Length(min = 1, max = 20, message = "xx不能为空s")
	private String	holderRoleName;

	public String getCardNo() {

		return cardNo;
	}

	public void setCardNo(String cardNo) {

		this.cardNo = cardNo;
	}

	public String getUserType() {

		return userType;
	}

	public void setUserType(String userType) {

		this.userType = userType;
	}

	public Long getUserId() {

		return userId;
	}

	public void setUserId(Long userId) {

		this.userId = userId;
	}

	public String getHolderRoleCode() {

		return holderRoleCode;
	}

	public void setHolderRoleCode(String holderRoleCode) {

		this.holderRoleCode = holderRoleCode;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

	public String getHolderRoleName() {

		return holderRoleName;
	}

	public void setHolderRoleName(String holderRoleName) {

		this.holderRoleName = holderRoleName;
	}

	@Override
	public String toString() {

		return "PointCardReq [cardNo=" + cardNo + ", userType=" + userType + ", userId=" + userId + ", holderRoleCode=" + holderRoleCode + ", versionNo=" + versionNo
				+ ", holderRoleName=" + holderRoleName + "]";
	}

}
