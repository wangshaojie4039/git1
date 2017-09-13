package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.BaseEntity;

public class TeacherSchoolMap extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = -9125810273835259278L;

    private Long schoolId;

    private Long teacherId;

    private String role;

    private Date createTime;

    private String creatorRole;

    private Long creatorId;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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
}