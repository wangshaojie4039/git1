package cn.imexue.ec.web.web.controller.child.req;

import javax.validation.constraints.NotNull;

public class ChildParentReq {

	@NotNull
	private Long childId;
	
	@NotNull
	private Long parentId;

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
