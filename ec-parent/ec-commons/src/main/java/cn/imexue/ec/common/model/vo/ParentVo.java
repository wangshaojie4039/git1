package cn.imexue.ec.common.model.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.Parent;

@Alias("ParentVo")
public class ParentVo extends Parent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7810887819217553138L;

	private String relationName;

	private String relationCode;
	
	private List<ChildVo> children;
	
	private Integer isDefault;
	
	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelationCode() {
		return relationCode;
	}

	public void setRelationCode(String relationCode) {
		this.relationCode = relationCode;
	}

	public List<ChildVo> getChildren() {
		return children;
	}

	public void setChildren(List<ChildVo> children) {
		this.children = children;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
}
