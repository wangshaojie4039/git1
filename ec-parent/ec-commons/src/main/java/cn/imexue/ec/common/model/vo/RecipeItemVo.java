package cn.imexue.ec.common.model.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import cn.imexue.ec.common.model.RecipeItem;

@Alias("RecipeItemVo")
public class RecipeItemVo extends RecipeItem{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7147394832954135742L;

	private Date recipeDate;

	public Date getRecipeDate() {
		return recipeDate;
	}

	public void setRecipeDate(Date recipeDate) {
		this.recipeDate = recipeDate;
	}
	
	public List<String> getItems(){
		List<String> items = new ArrayList<>();
		items.add(getItem1());
		items.add(getItem2());
		items.add(getItem3());
		items.add(getItem4());
		items.add(getItem5());
		items.add(getItem6());
		return items;
	}
	
}
