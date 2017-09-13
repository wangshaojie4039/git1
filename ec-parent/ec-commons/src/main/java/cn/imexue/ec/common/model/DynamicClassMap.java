package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

public class DynamicClassMap extends DataEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8538005823679004082L;

	private Long id;

    private Long dynamicId;

    private Long classId;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Long dynamicId) {
        this.dynamicId = dynamicId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}