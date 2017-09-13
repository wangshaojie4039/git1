package cn.imexue.ec.web.web.controller.device.req;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.device.req.DeviceVo.java<br/>
 * 初始作者： 崔业新<br/>
 * 创建日期： 2017年6月15日<br/>
 * 功能说明： 这里用一句话描述这个类的作用--此句话需删除 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
public class PointDeviceReq {

    private Long id;

    private Long classId;

    private String installLocation;

    private Integer versionNo;

    private Integer installAddress;// 如果是1就是分配到其他区域 **** 0分配到班级

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

    public String getInstallLocation() {

	return installLocation;
    }

    public void setInstallLocation(String installLocation) {

	this.installLocation = installLocation;
    }

    public Integer getVersionNo() {

	return versionNo;
    }

    public void setVersionNo(Integer versionNo) {

	this.versionNo = versionNo;
    }

    public Integer getInstallAddress() {

	return installAddress;
    }

    public void setInstallAddress(Integer installAddress) {

	this.installAddress = installAddress;
    }

}
