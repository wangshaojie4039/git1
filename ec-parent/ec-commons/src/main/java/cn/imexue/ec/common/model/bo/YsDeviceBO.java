package cn.imexue.ec.common.model.bo;

/**
 * 萤石云设备BO
 * 
 * Copyright ©2017 juziwl, All Rights Reserved.
 * 
 * @since  2017年3月4日
 * @author lijianfeng
 * @version 1.0
 */
public class YsDeviceBO {
	private String deviceSerial;//设备序列号
	private String deviceName;//设备名称
	private String deviceType;//设备类型
	private int status;//在线状态：0-不在线，1-在线
	private int isEncrypt;//是否加密：0-不加密，1-加密
	private int defence;//A1设备布撤防状态：0-睡眠，8-在家，16-外出，非A1设备布撤防状态：0-撤防，1-布防
	private String deviceVersion;//设备版本号
	
	public String getDeviceSerial() {
		return deviceSerial;
	}
	public void setDeviceSerial(String deviceSerial) {
		this.deviceSerial = deviceSerial;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIsEncrypt() {
		return isEncrypt;
	}
	public void setIsEncrypt(int isEncrypt) {
		this.isEncrypt = isEncrypt;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public String getDeviceVersion() {
		return deviceVersion;
	}
	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
}
