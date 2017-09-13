package cn.imexue.ec.common.model.vo;

import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.Child;

@Alias("ChildVo")
public class ChildVo extends Child {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -5920812000689253145L;

	private Integer				cardNum;

	private String				className;

	private String				reladtionShipName;

	private List<ParentVo>		parents;

	private Long				childId;

	private String relationName;
	
	private String relationCode;
	
	public Integer getCardNum() {

		return cardNum;
	}

	public void setCardNum(Integer cardNum) {

		this.cardNum = cardNum;
	}

	public String getClassName() {

		return className;
	}

	public void setClassName(String className) {

		this.className = className;
	}

	public List<ParentVo> getParents() {

		return parents;
	}

	public void setParents(List<ParentVo> parents) {

		this.parents = parents;
	}

	public String getReladtionShipName() {

		return reladtionShipName;
	}

	public void setReladtionShipName(String reladtionShipName) {

		this.reladtionShipName = reladtionShipName;
	}

	public Long getChildId() {

		return childId;
	}

	public void setChildId(Long childId) {

		this.childId = childId;
	}

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

}
