package cn.imexue.ec.common.model.vo;

import java.util.List;

import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.SchoolNotice;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.SchoolNoticeItemVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月1日</br>
 * 功能说明： 查看通知 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class SchoolNoticeItemVo extends SchoolNotice {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long				serialVersionUID	= -7159110042226622491L;

	private TeacherVo						teacherVo;

	private School							school;

	private List<SchoolNoticeReceiverVo>	hasReadSchoolNoticeReceiver;
	private List<SchoolNoticeReceiverVo>	notReadSchoolNoticeReceiver;

	public TeacherVo getTeacherVo() {

		return teacherVo;
	}

	public void setTeacherVo(TeacherVo teacherVo) {

		this.teacherVo = teacherVo;
	}

	public School getSchool() {

		return school;
	}

	public void setSchool(School school) {

		this.school = school;
	}

	public List<SchoolNoticeReceiverVo> getHasReadSchoolNoticeReceiver() {

		return hasReadSchoolNoticeReceiver;
	}

	public void setHasReadSchoolNoticeReceiver(List<SchoolNoticeReceiverVo> hasReadSchoolNoticeReceiver) {

		this.hasReadSchoolNoticeReceiver = hasReadSchoolNoticeReceiver;
	}

	public List<SchoolNoticeReceiverVo> getNotReadSchoolNoticeReceiver() {

		return notReadSchoolNoticeReceiver;
	}

	public void setNotReadSchoolNoticeReceiver(List<SchoolNoticeReceiverVo> notReadSchoolNoticeReceiver) {

		this.notReadSchoolNoticeReceiver = notReadSchoolNoticeReceiver;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

}
