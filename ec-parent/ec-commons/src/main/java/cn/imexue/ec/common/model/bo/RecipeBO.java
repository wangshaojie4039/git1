package cn.imexue.ec.common.model.bo;

import java.sql.Date;

/**
 * 食谱BO类
 *
 * Copyright ©2017 juziwl, All Rights Reserved.
 *
 * @since 2017年3月2日
 * @author wangshaojie
 * @version 1.0
 */
public class RecipeBO {

	private Long id;
	
	private Date recipeDate;
	
	private Long itemId;
	
	private String itemType;
	
	private String recipeImg;
	
	private String item1;
	
	private String item2;
	
	private String item3;
	
	private String item4;
	
	private String item5;
	
	private String item6
	
	
	
	;

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



	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getRecipeImg() {
		return recipeImg;
	}

	public void setRecipeImg(String recipeImg) {
		this.recipeImg = recipeImg;
	}

	public String getItem1() {
		return item1;
	}

	public void setItem1(String item1) {
		this.item1 = item1;
	}

	public String getItem2() {
		return item2;
	}

	public void setItem2(String item2) {
		this.item2 = item2;
	}

	public String getItem3() {
		return item3;
	}

	public void setItem3(String item3) {
		this.item3 = item3;
	}

	public String getItem4() {
		return item4;
	}

	public void setItem4(String item4) {
		this.item4 = item4;
	}

	public String getItem5() {
		return item5;
	}

	public void setItem5(String item5) {
		this.item5 = item5;
	}

	public String getItem6() {
		return item6;
	}

	public void setItem6(String item6) {
		this.item6 = item6;
	}

	@Override
	public String toString() {
		return "RecipeBO [id=" + id + ", recipeDate=" + recipeDate
				+ ", itemId=" + itemId + ", itemType=" + itemType
				+ ", recipeImg=" + recipeImg + ", item1=" + item1 + ", item2="
				+ item2 + ", item3=" + item3 + ", item4=" + item4 + ", item5="
				+ item5 + ", item6=" + item6 + "]";
	}
	
	
}
