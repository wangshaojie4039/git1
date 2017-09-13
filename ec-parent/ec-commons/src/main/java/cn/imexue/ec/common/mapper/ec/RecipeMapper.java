package cn.imexue.ec.common.mapper.ec;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.Recipe;
import cn.imexue.ec.common.model.RecipeItem;
import cn.imexue.ec.common.model.vo.RecipeItemVo;

@Mapper
public interface RecipeMapper {

	
	/**
	 * 
	 * @param map{start , schoolId , type(list) }
	 * @return
	 */
	List<RecipeItemVo> getRecipeItems(Map<String, Object> map); 
	
	RecipeItemVo getRecipeItemsById(Long id);
	
	RecipeItemVo getRecipeItemByDate(@Param("date")Date date,@Param("type")String type,@Param("schoolId")Long schoolId);
	
	void insert(RecipeItem item);
	
	void update(RecipeItem item);
	
	/**
	 * 按id删，或者按itemType和recipeId删
	 * @param item
	 */
	void delete(RecipeItem item);
	
	void deleteItemByRecipe(Long recipeId);
	
	Recipe getRecipe(@Param("date")Date date,@Param("schoolId")Long schoolId);
	
	void insertRecipe(Recipe recipe);
	
//	/**
//	 * 复制上一周
//	 * @param items
//	 */
//	void insert(List<RecipeItem> items);
	
}
