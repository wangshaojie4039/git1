package cn.imexue.ec.web.web.controller.notice.req;

import java.util.Date;

import cn.imexue.ec.common.model.page.PageQuery;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.notice.req.SchoolNoticeQuery.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年6月26日</br>
 * 功能说明：通知请求参数 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class SchoolNoticeQuery extends PageQuery {

	/**
	 * 学校ID
	 */
	private Long	schoolId;

	/**
	 * 标题
	 */
	private String	title;

	/**
	 * 开始时间
	 */
	private Date	startDate;

	/**
	 * 结束时间
	 */
	private Date	endDate;

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public Date getStartDate() {

		return startDate;
	}

	public void setStartDate(Date startDate) {

		this.startDate = startDate;
	}

	public Date getEndDate() {

		return endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

}
