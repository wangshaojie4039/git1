package cn.imexue.ec.web.web.controller.device.req;

import javax.validation.constraints.NotNull;

/**
 * 考勤基础信息请求类
 *
 * @author Administrator
 */
public class DeviceAttendanceBaseInfo {

	private Long	id;

	@NotNull
	private String	name;

	private Integer	versionNo;

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public Integer getVersionNo() {

		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {

		this.versionNo = versionNo;
	}

}
