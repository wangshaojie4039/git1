package cn.imexue.ec.common.model.vo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RecipeVo {

	private Long id;
	
	private Date recipeDate;
	
	private List<RecipeItemVo> recipes;

	private String weekdayStr = "";
	
	public static final String[] weekday = {"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getRecipeDate() {
		return recipeDate;
	}

	public void setRecipeDate(Date recipeDate) {
		this.recipeDate = recipeDate;
	}

	public List<RecipeItemVo> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<RecipeItemVo> recipes) {
		this.recipes = recipes;
	}
	
	public void setWeekdayStr(String weekdayStr) {
		this.weekdayStr = weekdayStr;
	}

	public String getWeekdayStr(){
		if(recipeDate == null)return weekdayStr;
		Calendar instance = Calendar.getInstance();
		instance.setTime(recipeDate);
		int w = instance.get(Calendar.DAY_OF_WEEK);
		if(w==1)return weekday[6];
		return weekday[w-2];
	}
	
}
