package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SysStaff extends BaseEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -9113957986934295149L;

	private String loginId;

    @JsonIgnore
    private String password;

    private String name;

    private String staffRole;

    private String logoUrl;

    private String mobile;

    private String tel;

    private String email;

    private String qq;

    private String deptName;

    private String position;

    private Byte sex;

    private Date birthday;

    private Byte hasLogin;

    private Date firstLoginTime;

    private Date lastLoginTime;

    private Byte allowLogin;

    private Byte isDelete;

    private Date createTime;
    
    ////////////////以下为自定义字段////////////////
    private String staffRoleName;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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

    public Byte getAllowLogin() {
        return allowLogin;
    }

    public void setAllowLogin(Byte allowLogin) {
        this.allowLogin = allowLogin;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getStaffRoleName() {
		return staffRoleName;
	}

	public void setStaffRoleName(String staffRoleName) {
		this.staffRoleName = staffRoleName;
	}
}