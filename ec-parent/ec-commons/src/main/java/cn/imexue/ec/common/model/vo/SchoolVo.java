package cn.imexue.ec.common.model.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.util.StringUtil;

@Alias("SchoolVo")
public class SchoolVo extends School{

	/**
	 * 
	 */
	private static final long serialVersionUID = -847679712350293585L;

	private String areaName;
	
	private Integer teacherNum;
	
	private Integer parentNum;
	
	private Integer childrenNum;
	
	private String customerName;
	
	private String customerTel;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(Integer teacherNum) {
		this.teacherNum = teacherNum;
	}

	public Integer getParentNum() {
		return parentNum;
	}

	public void setParentNum(Integer parentNum) {
		this.parentNum = parentNum;
	}

	public Integer getChildrenNum() {
		return childrenNum;
	}

	public void setChildrenNum(Integer childrenNum) {
		this.childrenNum = childrenNum;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	
	public List<String> getRecipes(){
		String string = super.getRecipeTypes();
		List<String> list =  new ArrayList<>();
		if(StringUtil.isBlank(string)){
			string="B|BP|D|DP|D2|D2P";
		}
		String[] split = string.split("\\|");
		list.addAll(Arrays.asList(split));
		return list;
	}

}
