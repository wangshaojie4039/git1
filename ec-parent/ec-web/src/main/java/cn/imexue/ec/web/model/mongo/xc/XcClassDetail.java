package cn.imexue.ec.web.model.mongo.xc;

import java.util.List;

public class XcClassDetail {

	private List<String> children;

	private String className;

	private Long classId;

	public List<String> getChildren() {
		return children;
	}

	public void setChildren(List<String> children) {
		this.children = children;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

}
