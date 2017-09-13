package cn.imexue.ec.web.web.controller.school.req;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SchoolSetting {

	@NotNull
	@Size(min=1)
	private List<String> recipes;
	
	@Min(1)
	private Integer versionNo;

	public List<String> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<String> recipes) {
		this.recipes = recipes;
	}

	public Integer getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(Integer versionNo) {
		this.versionNo = versionNo;
	}
	
}
