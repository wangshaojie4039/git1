package cn.imexue.ec.web.service.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.RecipeMapper;
import cn.imexue.ec.common.mapper.ec.SchoolMapper;
import cn.imexue.ec.common.model.Recipe;
import cn.imexue.ec.common.model.vo.RecipeItemVo;
import cn.imexue.ec.common.model.vo.RecipeVo;
import cn.imexue.ec.common.model.vo.SchoolVo;
import cn.imexue.ec.common.util.login.LoginUtil;

@Service
@Transactional(readOnly=true)
public class RecipeService {

	@Resource
	private RecipeMapper recipeMapper;
	
	@Resource
	private SchoolMapper schoolMapper;
	
	private static final List<String> WEEKDAYlIST = Arrays.asList(RecipeVo.weekday);
	
	/**
	 * 
	 * @exception 3010
	 */
	public List<RecipeVo> list(Date start,Long schoolId){
		//判断是否是周一
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(start);
		if(calendar.get(Calendar.DAY_OF_WEEK)!=2){
			//不是周一
			throw new AppChkException(3010, "recipe.start.nomonday",start);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("schoolId", schoolId);
		List<RecipeItemVo> recipeItems = recipeMapper.getRecipeItems(map);
		
		SchoolVo school = schoolMapper.getSchoolById(schoolId,null);
		List<String> recipes = school.getRecipes();
		//把不匹配type的项删除
		recipeItems = recipeItems.stream().filter(x -> recipes.contains(x.getItemType())).collect(Collectors.toList());
		
		//组合
		Map<Long, List<RecipeItemVo>> collect = recipeItems.stream().collect(Collectors.groupingBy(RecipeItemVo :: getRecipeId));
		List<RecipeVo> list = new ArrayList<>();
		for(Long id : collect.keySet()){
			RecipeVo recipe = new RecipeVo();
			recipe.setId(id);
			recipe.setRecipeDate(collect.get(id).get(0).getRecipeDate());
			recipe.setRecipes(collect.get(id));
			list.add(recipe);
		}
		
		for(String s : RecipeVo.weekday){
			boolean match = list.stream().anyMatch(x-> s.equals(x.getWeekdayStr()));
			if(!match){
				int indexOf = ArrayUtils.indexOf(RecipeVo.weekday, s);
				calendar.add(Calendar.DATE, indexOf);
				RecipeVo recipe = new RecipeVo();
				recipe.setWeekdayStr(s);
				recipe.setRecipeDate(calendar.getTime());
				recipe.setRecipes(new ArrayList<>());
				list.add(recipe);
				calendar.add(Calendar.DATE, -indexOf);
			}
			
		}
		
		list.sort((a,b)->a.getRecipeDate().compareTo(b.getRecipeDate()));
//		list.sort((a,b)->WEEKDAYlIST.indexOf(a.getWeekdayStr())-(WEEKDAYlIST.indexOf(b.getWeekdayStr())));
		
		return list;
	}
	
	
	public RecipeItemVo getItem(Long id){
		return recipeMapper.getRecipeItemsById(id);
	}
	
	public RecipeItemVo getItem(String itemType,Date itemDate,Long schoolId){
		
		return recipeMapper.getRecipeItemByDate(itemDate, itemType, schoolId);
	}
	
	/**
	 * 保存
	 * @param recipeItem
	 */
	@Transactional
	public void save(RecipeItemVo recipeItem,Long schoolId){
		RecipeItemVo itemByDate = recipeMapper.getRecipeItemByDate(recipeItem.getRecipeDate(),
				recipeItem.getItemType(),schoolId);
		if(itemByDate==null){
			insert(recipeItem);
		}else{
			if(itemByDate.getId().equals(recipeItem.getId())){
				update(recipeItem);
			}else{
				throw new RuntimeException("id不匹配");
			}
		}
		
	}
	/**
	 * 删除
	 * @param recipeItem
	 */
	@Transactional
	public void delete(RecipeItemVo recipeItem,Long schoolId){
		
		RecipeItemVo recipeItems = recipeMapper.getRecipeItemsById(recipeItem.getId());
		Assert.notNull(recipeItems,"未找到数据");
		Recipe recipe = recipeMapper.getRecipe(recipeItems.getRecipeDate(), schoolId);
		Assert.notNull(recipe,"未找到数据");
		Assert.state(recipe.getId().equals(recipeItems.getRecipeId()), "未找到数据");
		
		recipeMapper.delete(recipeItem);
	}
	
	/**
	 * 复制上周食谱
	 * @param startCopyToDay 复制目标周的第一天
	 * @param schoolId 
	 * @exception 3011 上周没有食谱
	 */
	@Transactional
	public void copyLastWeek(Date startCopyToDay,Long schoolId){
		//全复制
//		SchoolVo school = schoolMapper.getSchoolById(schoolId);
		
		startCopyToDay = getRecipeDate(startCopyToDay);
		Calendar instance = Calendar.getInstance();
		instance.setTime(startCopyToDay);
		instance.add(Calendar.DATE, -7);
		//复制源周的第一天
		Date time = instance.getTime();
		Map<String, Object> map = new HashMap<>();
		map.put("schoolId", schoolId);
		//先删除原来的数据
		map.put("start", startCopyToDay);
		List<RecipeItemVo> recipeItems = recipeMapper.getRecipeItems(map);
		recipeItems.forEach(x->recipeMapper.delete(x));
		//新增食谱
		map.put("start", time);
//		map.put("type", school.getRecipes());
		recipeItems = recipeMapper.getRecipeItems(map);
		recipeItems.forEach(x->add7Day(x));
		recipeItems.forEach(x->insert(x));
		if(recipeItems.size()<1){
			throw new AppChkException(3011, "recipe.week.noData");
		}
		
	}
	
	
	private void insert(RecipeItemVo recipeItem){
		Long schoolId = LoginUtil.getSchoolId();
		Recipe recipe = recipeMapper.getRecipe(recipeItem.getRecipeDate(), schoolId);
		if(recipe==null){
			//如果没有当日食谱,先增加食谱项
			recipe = new Recipe();
			Date date = getRecipeDate(recipeItem.getRecipeDate());
			recipe.setRecipeDate(date);
			recipe.setSchoolId(schoolId);
			recipeMapper.insertRecipe(recipe);
		}
		
		recipeItem.setRecipeId(recipe.getId());
		recipeMapper.insert(recipeItem);
	}
	
	private void update(RecipeItemVo recipeItem){
		recipeMapper.update(recipeItem);
	}
	
	private Date getRecipeDate(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	
	private void add7Day(RecipeItemVo recipeItem){
		Date recipeDate = recipeItem.getRecipeDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(recipeDate);
		cal.add(Calendar.DAY_OF_WEEK, 7);
		Date time = cal.getTime();
		recipeItem.setRecipeDate(time);
	}
	
	public static void main(String[] args) {
		List<String> list = Arrays.asList(RecipeVo.weekday);
		
		list.sort((a,b)->WEEKDAYlIST.indexOf(a)-(WEEKDAYlIST.indexOf(b)));
		list.forEach(a->System.out.println(a));
		
	}
}
