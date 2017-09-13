package cn.imexue.ec.web.web.controller.xc.xcLine.req;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineChildReq.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月19日<br/>
 * 功能说明： 校车线路-校车幼儿修改 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class XcLineChildReq {

	private Long	xcChildId;

	/**
	 * 版本号
	 */
	private Integer	versionNo;

	/**
	 * 学校id
	 */
	private Long	schoolId;

	/**
	 * 线路id
	 */
	private Long	lineId;

	public Long getXcChildId() {

		return xcChildId;
	}

	public void setXcChildId(Long xcChildId) {

		this.xcChildId = xcChildId;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

}
