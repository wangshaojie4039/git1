package cn.imexue.ec.common.model;

import java.util.Date;

import cn.imexue.ec.common.model.common.DataEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SchoolNotice extends DataEntity {

	/**
	 * 字段描述: [字段功能描述]
	 */
	private static final long	serialVersionUID	= 6789156696204967697L;

	/**
	 * 学校ID
	 */

	private Long				schoolId;

	/**
	 * 班级ID
	 */
	private Long				classId;

	/**
	 * 是否全部
	 */
	private Byte				isToAll;

	/**
	 * 标题
	 */
	private String				title;

	/**
	 * 内容
	 */
	private String				content;

	/**
	 * 老师ID
	 */
	private Long				teacherId;

	/**
	 * 发送时间
	 */
	private Date				createTime;

	/**
	 * 是否删除
	 */
	@JsonIgnore
	private Byte				isDelete;

	/**
	 * 删除时间
	 */
	@JsonIgnore
	private Date				deleteTime;

	public Long getSchoolId() {

		return schoolId;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public Long getClassId() {

		return classId;
	}

	public void setClassId(Long classId) {

		this.classId = classId;
	}

	public Byte getIsToAll() {

		return isToAll;
	}

	public void setIsToAll(Byte isToAll) {

		this.isToAll = isToAll;
	}

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getContent() {

		return content;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public Long getTeacherId() {

		return teacherId;
	}

	public void setTeacherId(Long teacherId) {

		this.teacherId = teacherId;
	}

	@Override
	public Date getCreateTime() {

		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {

		this.createTime = createTime;
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
