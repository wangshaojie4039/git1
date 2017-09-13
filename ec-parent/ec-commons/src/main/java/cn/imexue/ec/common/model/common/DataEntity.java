package cn.imexue.ec.common.model.common;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;



public class DataEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3439833810593326959L;

	@JsonIgnore
    private Long creatorId;

	@JsonIgnore
    private Date createTime;

	@JsonIgnore
	private String creatorRole;
	
	@JsonIgnore
    private Long updaterId;

	@JsonIgnore
    private Date updateTime;

	@JsonIgnore
	private String updaterRole;

	private Integer versionNo;
	
	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreatorRole() {
		return creatorRole;
	}

	public void setCreatorRole(String creatorRole) {
		this.creatorRole = creatorRole;
	}

	public Long getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdaterRole() {
		return updaterRole;
	}

	public void setUpdaterRole(String updaterRole) {
		this.updaterRole = updaterRole;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}

}