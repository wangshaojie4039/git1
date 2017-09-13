package cn.imexue.ec.web.model.mongo;

import java.util.Date;
import java.util.List;

import cn.imexue.ec.web.model.mongo.xc.XcAttendanceDetail;
import cn.imexue.ec.web.model.mongo.xc.XcLineStatistics;
import cn.imexue.ec.web.web.excel.ExcelExportProperties;
import cn.imexue.ec.web.web.excel.ExcelExportType;

@ExcelExportType(url="static/乘车统计.xls",firstLine=3)
public class XcAttendanceStatistics {

	@ExcelExportProperties(columnName="日期",order=1,dateFormat="yyyy-MM-dd")
	private Date attDate;

	@ExcelExportProperties(columnName="上学应乘",order=2)
	private Integer upTotal;//上学应乘

	@ExcelExportProperties(columnName="上学实乘",order=3)
	private Integer up;//上学实乘

	@ExcelExportProperties(columnName="放学应乘",order=4)
	private Integer downTotal;//放学应乘

	@ExcelExportProperties(columnName="放学实乘",order=5)
	private Integer down;

	private List<XcLineStatistics> lines;

	private XcAttendanceDetail upList;

	private XcAttendanceDetail downList;

	public Date getAttDate() {
		return attDate;
	}

	public void setAttDate(Date attDate) {
		this.attDate = attDate;
	}

	public Integer getUpTotal() {
		return upTotal;
	}

	public void setUpTotal(Integer upTotal) {
		this.upTotal = upTotal;
	}

	public Integer getUp() {
		return up;
	}

	public void setUp(Integer up) {
		this.up = up;
	}

	public Integer getDownTotal() {
		return downTotal;
	}

	public void setDownTotal(Integer downTotal) {
		this.downTotal = downTotal;
	}

	public Integer getDown() {
		return down;
	}

	public void setDown(Integer down) {
		this.down = down;
	}

	public List<XcLineStatistics> getLines() {
		return lines;
	}

	public void setLines(List<XcLineStatistics> lines) {
		this.lines = lines;
	}

	public XcAttendanceDetail getUpList() {
		return upList;
	}

	public void setUpList(XcAttendanceDetail upList) {
		this.upList = upList;
	}

	public XcAttendanceDetail getDownList() {
		return downList;
	}

	public void setDownList(XcAttendanceDetail downList) {
		this.downList = downList;
	}

}
