package cn.imexue.ec.common.model.bo;

public class ChildAndClassBO {

	private Long childId;
	
	private String childName;
	
	private Long classId;
	
	private String className;

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
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

	@Override
	public String toString() {
		return "ChildAndClassBO [childId=" + childId + ", childName="
				+ childName + ", classId=" + classId + ", className="
				+ className + "]";
	}
	
	
	
	
}
