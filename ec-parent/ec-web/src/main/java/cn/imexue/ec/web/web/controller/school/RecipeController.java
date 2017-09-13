package cn.imexue.ec.web.web.controller.school;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.vo.RecipeItemVo;
import cn.imexue.ec.common.model.vo.RecipeVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.school.RecipeService;
import cn.imexue.ec.web.web.controller.school.req.RecipeReq;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@RestController
@Role(RoleType.D)
@RequestMapping("recipe")
public class RecipeController {

	@Resource
	private RecipeService recipeService;
	
	/**
	 * @api {POST} /recipe/list 食谱列表
	 * @apiGroup recipe
	 * @apiName recipeList
	 * 
	 * @apiParam {Date} start 日期[yyyy-MM-dd hh:mm:ss](查询的第一天，必须为周一)
	 * @apiParamExample {json} Request-Example:
	 * {
		  "start": "2017-06-19 00:00:00"
		}
	 * 
	 * @apiError 3010 不是周一
	 * @apiSuccess {String} itemType B早餐BP早加餐D午加餐DP午餐D2晚餐D2P晚加餐
	 * @apiSuccess {json} recipes 每日食谱
	 * @apiSuccessExample {json}  Success-Response:
	 * {
      "id": 3,
      "recipeDate": "2017-06-19 00:00:00",
      "recipes": [
        {
          "id": 4,
          "versionNo": 1,
          "recipeId": 3,
          "itemType": "B",
          "recipeImg": "image/2017/3/15/08ef81fa-07d7-4f36-924d-83b7a94ed7db.png",
          "item1": "ewrwer",
          "item2": "",
          "item3": "",
          "item4": "",
          "item5": "",
          "item6": "",
          "recipeDate": "2017-06-19 00:00:00"
        },
        {
          "id": 6,
          "versionNo": 1,
          "recipeId": 3,
          "itemType": "BP",
          "recipeImg": "",
          "item1": "鸡蛋西红柿",
          "item2": "鸡蛋西红柿1",
          "item3": "鸡蛋西红柿2",
          "item4": "鸡蛋西红柿3",
          "item5": "",
          "item6": "",
          "recipeDate": "2017-06-19 00:00:00"
        }
      ],
      "weekdayStr": "星期一"
    },
    {
      "id": 4,
      "recipeDate": "2017-06-20 00:00:00",
      "recipes": [
        {
          "id": 7,
          "versionNo": 1,
          "recipeId": 4,
          "itemType": "BP",
          "recipeImg": "",
          "item1": "",
          "item2": "",
          "item3": "",
          "item4": "",
          "item5": "",
          "item6": "",
          "recipeDate": "2017-06-19 00:00:00"
        }
      ],
      "weekdayStr": "星期二"
    }
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public RespJson list(@RequestBody RecipeReq recipeReq){
		Date start = recipeReq.getStart();
		Assert.notNull(start, "时间不能为空");
		Long schoolId = LoginUtil.getSchoolId();
		
		List<RecipeVo> list = recipeService.list(start, schoolId);
		
		return RespJsonFactory.buildSuccess(list);
	}
	
	/**
	 * @api {POST} /recipe/get/{id} 食谱详细
	 * @apiGroup recipe
	 * @apiName recipeGet
	 * 
	 * 
	 * @apiParam {String} itemType 
	 * @apiParam {date} recipeDate 
	 * 
	 * @apiSuccess {String} itemType B早餐BP早加餐D午加餐DP午餐D2晚餐D2P晚加餐
	 * @apiSuccessExample {json}  Success-Response:
	 * {
	    "id": 4,
	    "versionNo": 1,
	    "recipeId": 3,
	    "itemType": "B",
	    "recipeImg": "image/2017/3/15/08ef81fa-07d7-4f36-924d-83b7a94ed7db.png",
	    "item1": "ewrwer",
	    "item2": "",
	    "item3": "",
	    "item4": "",
	    "item5": "",
	    "item6": "",
	    "recipeDate": "2017-06-19 00:00:00"
	  }
	 */
	@RequestMapping(value="get",method=RequestMethod.POST)
	public RespJson get(@RequestBody RecipeReq req ){
		String itemType = req.getItemType();
		Date recipeDate = req.getRecipeDate();
		Long schoolId = LoginUtil.getSchoolId();
		RecipeItemVo item = recipeService.getItem(itemType, recipeDate, schoolId);
		
		return RespJsonFactory.buildSuccess(item);
	}
	
	/**
	 * @api {POST} /recipe/save 保存食谱
	 * @apiGroup recipe
	 * @apiName recipeSave
	 * 
	 * @apiParamExample {json} Request-Example:
	 * {
	    "id": 4,
	    "versionNo": 1,
	    "recipeId": 3,
	    "itemType": "B",
	    "recipeImg": "image/2017/3/15/08ef81fa-07d7-4f36-924d-83b7a94ed7db.png",
	    "item1": "ewrwer",
	    "item2": "aaaa",
	    "item3": "",
	    "item4": "cccc",
	    "item5": "",
	    "item6": "",
	    "recipeDate": "2017-06-19 00:00:00"
	  }

	 * 
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public RespJson save(@RequestBody @Valid RecipeReq recipeReq){
		RecipeItemVo item = new RecipeItemVo();
		BeanUtils.copyProperties(recipeReq, item);
		
		Long schoolId = LoginUtil.getSchoolId();
		recipeService.save(item,schoolId);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} /recipe/copyWeek 复制上一周食谱
	 * @apiGroup recipe
	 * @apiName recipeCopyWeek
	 * 
	 * @apiParam {Date} start 日期[yyyy-MM-dd hh:mm:ss](需要填入数据周的第一天，必须为周一)
	 * @apiParamExample {json} Request-Example:
	 * {
		  "start": "2017-06-19 00:00:00"
	   }
	 * 
	 * 
	 */
	@RequestMapping(value="copyWeek",method=RequestMethod.POST)
	public RespJson copyWeek(@RequestBody RecipeReq recipeReq){
		Date start = recipeReq.getStart();
		Assert.notNull(start, "时间不能为空");
		
		Long schoolId = LoginUtil.getSchoolId();
		recipeService.copyLastWeek(start, schoolId);
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} /recipe/delete/{id} 删除食谱
	 * @apiGroup recipe
	 * @apiName recipeDelete
	 * 
	 */
	@RequestMapping(value="delete/{id}",method=RequestMethod.POST)
	public RespJson delete(@PathVariable("id")Long id){
		RecipeItemVo item = new RecipeItemVo();
		item.setId(id);
		Long schoolId = LoginUtil.getSchoolId();
		recipeService.delete(item,schoolId);
		return RespJsonFactory.buildSuccess();
	}
	
	
}
