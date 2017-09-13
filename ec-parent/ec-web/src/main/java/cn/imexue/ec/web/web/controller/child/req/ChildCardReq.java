package cn.imexue.ec.web.web.controller.child.req;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class ChildCardReq {

	private Long cardId;
	
	private Integer versionNo;
	
	@Length(max=10,min=10)
	private String cardNo;

	@NotNull
	private Long childId;
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getChildId() {
		return childId;
	}

	public void setChildId(Long childId) {
		this.childId = childId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	
}
