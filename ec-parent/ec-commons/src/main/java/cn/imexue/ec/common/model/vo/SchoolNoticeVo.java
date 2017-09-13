package cn.imexue.ec.common.model.vo;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.SchoolNotice;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.NoticeVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年6月26日</br>
 * 功能说明： 通知列表vo <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("SchoolNoticeVo")
public class SchoolNoticeVo extends SchoolNotice {

	private static final long	serialVersionUID	= -7976675755480759103L;

	private Integer				totalClass;									// 班级数目

	private TeacherInfoVo		teacherInfo;									// 老师

	private Integer				readNum;										// 已读数目

	private Integer				totalNum;										// 接收人数目，发送范围班级人数

	public Integer getTotalClass() {

		return totalClass;
	}

	public void setTotalClass(Integer totalClass) {

		this.totalClass = totalClass;
	}

	public static long getSerialversionuid() {

		return serialVersionUID;
	}

	public Integer getReadNum() {

		return readNum;
	}

	public void setReadNum(Integer readNum) {

		this.readNum = readNum;
	}

	public Integer getTotalNum() {

		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {

		this.totalNum = totalNum;
	}

	public TeacherInfoVo getTeacherInfo() {

		return teacherInfo;
	}

	public void setTeacherInfo(TeacherInfoVo teacherInfo) {

		this.teacherInfo = teacherInfo;
	}

}
