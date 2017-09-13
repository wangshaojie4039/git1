package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

public class Class extends DataEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2864665037727307107L;

	private Long schoolId;

    private String name;

    private String schoolYear;

    private Long masterTeacherId;

    private Date lastPromoteTime;

    private Byte isGraduate;

    private Date graduateTime;

    private Byte isDelete;

    private Date deleteTime;

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Long getMasterTeacherId() {
        return masterTeacherId;
    }

    public void setMasterTeacherId(Long masterTeacherId) {
        this.masterTeacherId = masterTeacherId;
    }

    public Date getLastPromoteTime() {
        return lastPromoteTime;
    }

    public void setLastPromoteTime(Date lastPromoteTime) {
        this.lastPromoteTime = lastPromoteTime;
    }

    public Byte getIsGraduate() {
        return isGraduate;
    }

    public void setIsGraduate(Byte isGraduate) {
        this.isGraduate = isGraduate;
    }

    public Date getGraduateTime() {
        return graduateTime;
    }

    public void setGraduateTime(Date graduateTime) {
        this.graduateTime = graduateTime;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

}