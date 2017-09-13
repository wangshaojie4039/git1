package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.common.DataEntity;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcChildItemVo.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月19日<br/>
 * 功能说明： 班级小孩对应vo <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcChildItemVo")
public class XcChildItemVo extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= -9129069394427927943L;

	private Long				id;

	private Long				classId;

	private String				childIds;

	@Override
	public Long getId() {

		return id;
	}

	@Override
	public void setId(Long id) {

		this.id = id;
	}

	public Long getClassId() {

		return classId;
	}

	public void setClassId(Long classId) {

		this.classId = classId;
	}

	public String getChildIds() {

		return childIds;
	}

	public void setChildIds(String childIds) {

		this.childIds = childIds;
	}

}
