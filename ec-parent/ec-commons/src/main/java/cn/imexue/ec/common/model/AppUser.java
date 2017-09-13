package cn.imexue.ec.common.model;

import java.util.Date;

public class AppUser {
	private Long id;

    private String mobile;

    private String uid;
    
    private String uuid;

    private String password;

    private String name;

    private String logoUrl;

    private Byte sex;

    private Date birthday;

    private Byte hasLogin;

    private Date firstLoginTime;

    private Date lastLoginTime;

    private Byte isActive;

    private Date createTime;

    private String creatorRole;

    private Long creatorId;

    private Date updateTime;

    private String updaterRole;

    private Long updaterId;
    
    protected String role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUid() {
		return uid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Byte getHasLogin() {
		return hasLogin;
	}

	public void setHasLogin(Byte hasLogin) {
		this.hasLogin = hasLogin;
	}

	public Date getFirstLoginTime() {
		return firstLoginTime;
	}

	public void setFirstLoginTime(Date firstLoginTime) {
		this.firstLoginTime = firstLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Byte getIsActive() {
		return isActive;
	}

	public void setIsActive(Byte isActive) {
		this.isActive = isActive;
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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
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

	public Long getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Long updaterId) {
		this.updaterId = updaterId;
	}
	
	@Override
	public String toString() {
		return "AppUser [id=" + id + ", mobile=" + mobile + ", uid=" + uid
				+ ", uuid=" + uuid + ", password=" + password + ", name="
				+ name + ", logoUrl=" + logoUrl + ", sex=" + sex
				+ ", birthday=" + birthday + ", hasLogin=" + hasLogin
				+ ", firstLoginTime=" + firstLoginTime + ", lastLoginTime="
				+ lastLoginTime + ", isActive=" + isActive + ", createTime="
				+ createTime + ", creatorRole=" + creatorRole + ", creatorId="
				+ creatorId + ", updateTime=" + updateTime + ", updaterRole="
				+ updaterRole + ", updaterId=" + updaterId + ", role=" + role
				+ "]";
	}
	
	
	
	
}
