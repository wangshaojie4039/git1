package cn.imexue.ec.web.web.controller.school.req;

import java.util.List;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

public class ClassReq {

	private Long id;
	
	@NotBlank
	private String name;
	
	private Long masterId;

	private List<Long> teacherIds;

	@Min(1)
	private Integer versionNo;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getMasterId() {
		return masterId;
	}

	public void setMasterId(Long masterId) {
		this.masterId = masterId;
	}

	public List<Long> getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(List<Long> teacherIds) {
		this.teacherIds = teacherIds;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
}
