package cn.imexue.ec.common.model;

import java.util.Date;

public class ClassPromoteRecord {
    private Long id;

    private Long classId;

    private String previousClassName;

    private String currentClassName;

    private String previousSchoolYear;

    private String currentSchoolYear;

    private Byte isGraduate;

    private Date createTime;

    private String creatorRole;

    private Long creatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getPreviousClassName() {
        return previousClassName;
    }

    public void setPreviousClassName(String previousClassName) {
        this.previousClassName = previousClassName;
    }

    public String getCurrentClassName() {
        return currentClassName;
    }

    public void setCurrentClassName(String currentClassName) {
        this.currentClassName = currentClassName;
    }

    public String getPreviousSchoolYear() {
        return previousSchoolYear;
    }

    public void setPreviousSchoolYear(String previousSchoolYear) {
        this.previousSchoolYear = previousSchoolYear;
    }

    public String getCurrentSchoolYear() {
        return currentSchoolYear;
    }

    public void setCurrentSchoolYear(String currentSchoolYear) {
        this.currentSchoolYear = currentSchoolYear;
    }

    public Byte getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(Byte isGraduate) {
        this.isGraduate = isGraduate;
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