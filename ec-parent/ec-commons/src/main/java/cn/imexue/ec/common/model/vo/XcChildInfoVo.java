package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcChildInfoVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月24日<br/>
 * 功能说明： 校车child基础信息 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcChildInfoVo")
public class XcChildInfoVo {

	/**
	 * 校车幼儿id
	 */
	private Long	id;

	/**
	 * 幼儿名称
	 */
	private String	childName;
	/**
	 * 幼儿ID
	 */
	private Long	childId;

	/**
	 * 版本号
	 */
	private Integer	versionNo;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getChildName() {

		return childName;
	}

	public void setChildName(String childName) {

		this.childName = childName;
	}

	public Long getChildId() {

		return childId;
	}

	public void setChildId(Long childId) {

		this.childId = childId;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

}
