package cn.imexue.ec.common.model;

import java.util.Date;

public class DeviceCameraIpc {
    private Long id;

    private Long cameraId;

    private String ipcSerial;

    private Integer channelNo;

    private String validateCode;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCameraId() {
        return cameraId;
    }

    public void setCameraId(Long cameraId) {
        this.cameraId = cameraId;
    }

    public String getIpcSerial() {
        return ipcSerial;
    }

    public void setIpcSerial(String ipcSerial) {
        this.ipcSerial = ipcSerial;
    }

    public Integer getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(Integer channelNo) {
        this.channelNo = channelNo;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}