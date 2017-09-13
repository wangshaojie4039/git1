package cn.imexue.ec.web.model.mongo;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import cn.imexue.ec.web.web.excel.ExcelExportProperties;

public class ChildAttendanceClassHistory {

	@Id
	private ObjectId id;
	
	private Long schoolId;
	
	@ExcelExportProperties(columnName="日期",order=1,dateFormat="yyyy-MM-dd")
	private Date date;
	
	private Long classId;
	
	private List<ChildAttendanceDto> normals = new ArrayList<>();
	
	private List<ChildAttendanceDto> absenteeisms = new ArrayList<>();

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public List<ChildAttendanceDto> getNormals() {
		return normals;
	}

	public void setNormals(List<ChildAttendanceDto> normals) {
		this.normals = normals;
	}

	public List<ChildAttendanceDto> getAbsenteeisms() {
		return absenteeisms;
	}

	public void setAbsenteeisms(List<ChildAttendanceDto> absenteeisms) {
		this.absenteeisms = absenteeisms;
	}

	public Long getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Long schoolId) {
		this.schoolId = schoolId;
	}
	
	@ExcelExportProperties(columnName="班级人数",order=2)
	public int totalNum(){
		return absenteeisms.size()+normals.size();
	}
	
	//出勤率
	@ExcelExportProperties(columnName="出勤率",order=3)
	public String attendVal(){
		if(totalNum()==0){
			return "0%";
		}
		BigDecimal divide = new BigDecimal(attendNum()).divide(new BigDecimal(totalNum()));
		NumberFormat nf   =   NumberFormat.getPercentInstance();
		nf.setMaximumFractionDigits(2);
		
		return nf.format(divide.doubleValue());
	}
	
	@ExcelExportProperties(columnName="出勤数",order=4)
	public int attendNum(){
		return normals.size();
	}
	
	@ExcelExportProperties(columnName="缺勤人数",order=5)
	public int absenteeismsSize(){
		return absenteeisms.size();
	}
	
}
