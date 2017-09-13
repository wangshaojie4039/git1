package cn.imexue.ec.common.model;

import java.util.Date;

public class SchoolNoticeClass {
    private Long id;

    private Long schoolId;

    private Long schoolNoticeId;

    private Long classId;

    private String className;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getSchoolNoticeId() {
        return schoolNoticeId;
    }

    public void setSchoolNoticeId(Long schoolNoticeId) {
        this.schoolNoticeId = schoolNoticeId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}