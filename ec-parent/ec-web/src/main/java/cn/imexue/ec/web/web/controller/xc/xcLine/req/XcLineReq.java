package cn.imexue.ec.web.web.controller.xc.xcLine.req;

import java.util.List;

import javax.validation.constraints.NotNull;

import cn.imexue.ec.common.model.common.DataEntity;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineReq.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明：校车线路添加model <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcLineReq extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 5414537116115286218L;

	private List<Long> ids;

	private Long schoolId;
	@NotNull(message = "线路名称不能为空")
	private String lineName;

	private Long teacherId;

	private Byte isDelete = 0;

	public List<Long> getIds() {

		return ids;
	}

	public void setIds(List<Long> ids) {

		this.ids = ids;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public String getLineName() {

		return lineName;
	}

	public void setLineName(String lineName) {

		this.lineName = lineName;
	}

	public Long getTeacherId() {

		return teacherId;
	}

	public void setTeacherId(Long teacherId) {

		this.teacherId = teacherId;
	}

	public Byte getIsDelete() {

		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {

		this.isDelete = isDelete;
	}

}
