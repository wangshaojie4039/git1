package cn.imexue.ec.web.api.model;

import java.util.List;

import org.hibernate.validator.constraints.Length;

public class NoticeSendApi {

	@Length(min = 1, max = 30, message = "标题字数不正确")
	private String			title;
	@Length(min = 1, max = 400, message = "内容字数不正确")
	private String			content;
	private Long			schoolId;
	private List<Long>		classIds;
	private List<String>	teacherIds;

	public String getTitle() {

		return title;
	}

	public String getContent() {

		return content;
	}

	public Long getSchoolId() {

		return schoolId;
	}

	public List<Long> getClassIds() {

		return classIds;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public void setContent(String content) {

		this.content = content;
	}

	public void setSchoolId(Long schoolId) {

		this.schoolId = schoolId;
	}

	public void setClassIds(List<Long> classIds) {

		this.classIds = classIds;
	}

	public List<String> getTeacherIds() {

		return teacherIds;
	}

	public void setTeacherIds(List<String> teacherIds) {

		this.teacherIds = teacherIds;
	}

}
