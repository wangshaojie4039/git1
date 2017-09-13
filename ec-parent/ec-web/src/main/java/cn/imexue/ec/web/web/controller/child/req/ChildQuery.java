package cn.imexue.ec.web.web.controller.child.req;

import cn.imexue.ec.common.model.page.PageQuery;

public class ChildQuery extends PageQuery {

	private Long classId;
	
	private String name;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
