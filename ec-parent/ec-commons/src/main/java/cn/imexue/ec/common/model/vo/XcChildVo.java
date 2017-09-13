package cn.imexue.ec.common.model.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

/**
 * 文件名称： cn.imexue.ec.common.model.vo.XcChildVo.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 校车查询幼儿Vo<br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Alias("XcChildVo")
public class XcChildVo {

	/**
	 * ID
	 */
	private Long			id;

	/**
	 * 线路id
	 */
	private Long			lineId;
	/**
	 * 线路名称
	 */
	private String			lineName;

	/**
	 * 幼儿名称
	 */
	private String			name;

	/**
	 * 幼儿ID
	 */
	private Long			childId;

	/**
	 * 幼儿性别
	 */
	private Byte			sex;

	/**
	 * 班级ID
	 */
	private Long			classId;
	/**
	 * 班级名称
	 */
	private String			className;

	/**
	 * 监护人家长
	 */
	private List<ParentVo>	parentVo;

	public Long getId() {

		return id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Byte getSex() {

		return sex;
	}

	public void setSex(Byte sex) {

		this.sex = sex;
	}

	public Long getClassId() {

		return classId;
	}

	public void setClassId(Long classId) {

		this.classId = classId;
	}

	public String getClassName() {

		return className;
	}

	public void setClassName(String className) {

		this.className = className;
	}

	public List<ParentVo> getParentVo() {

		return parentVo;
	}

	public void setParentVo(List<ParentVo> parentVo) {

		this.parentVo = parentVo;
	}

	public Long getChildId() {

		return childId;
	}

	public void setChildId(Long childId) {

		this.childId = childId;
	}

	public Long getLineId() {

		return lineId;
	}

	public void setLineId(Long lineId) {

		this.lineId = lineId;
	}

	public String getLineName() {

		return lineName;
	}

	public void setLineName(String lineName) {

		this.lineName = lineName;
	}

}
