package cn.imexue.ec.web.web.controller.xc.xcChild.req;

import java.util.List;

import javax.validation.constraints.NotNull;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 添加幼儿Model <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcChildReq {

	private Long		schoolId;
	@NotNull(message = "线路id不能为空")
	private Long		lineId;

	@NotNull
	private List<Long>	ids;

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

	public List<Long> getIds() {

		return ids;
	}

	public void setIds(List<Long> ids) {

		this.ids = ids;
	}

}
