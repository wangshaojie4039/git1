package cn.imexue.ec.web.web.controller.xc.resp;

import java.util.Date;

import cn.imexue.ec.web.web.excel.ExcelExportProperties;

public class XcChildAttendanceExcelResp {

	@ExcelExportProperties(columnName="班级",order=1)
	private String className;
	
	@ExcelExportProperties(columnName="姓名",order=2)
	private String userName;
	
	@ExcelExportProperties(columnName="姓别",order=3)
	private String sex;
	
	@ExcelExportProperties(columnName="刷卡时间",order=4,dateFormat="yyyy-MM-dd HH:mm:ss")
	private Date swipeTime;
	
	@ExcelExportProperties(columnName="考勤类型",order=5)
	private String attType;
	
	@ExcelExportProperties(columnName="乘坐车辆",order=6)
	private String plateNumber;
	
	@ExcelExportProperties(columnName="线路",order=7)
	private String lineName;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getSwipeTime() {
		return swipeTime;
	}

	public void setSwipeTime(Date swipeTime) {
		this.swipeTime = swipeTime;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getLineName() {
		return lineName;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	
	public void setAttType(Integer attType) {
		switch (attType) {
			case 1:
				this.attType = "上学上车";
				break;
			case 2:
				this.attType = "上学下车";
				break;
			case 3:
				this.attType = "放学上车";
				break;
			case 4:
				this.attType = "放学下车";
				break;
		}
	}
}
