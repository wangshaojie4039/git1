package cn.imexue.ec.web.web.controller.school;

import io.swagger.annotations.Api;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.vo.SchoolVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.school.SchoolService;
import cn.imexue.ec.web.web.controller.school.req.SchoolReq;
import cn.imexue.ec.web.web.controller.school.req.SchoolSetting;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@Api
@RestController
@Role(RoleType.D)
@RequestMapping("school")
public class SchoolController {

	@Resource
	private SchoolService schoolService;
	
	/**
	 * @api {GET} /school/info 学校信息,学校设置
	 * @apiGroup school
	 * @apiName schoolInfo
	 * 
	 * @apiSuccess id 
	 * @apiSuccess name 学校名字
	 * @apiSuccess address 学校地址
	 * @apiSuccess linkman 联系人
	 * @apiSuccess tel 联系电话
	 * @apiSuccess logoUrl logo
	 * @apiSuccess teacherNum 教师人数
	 * @apiSuccess parentNum 家长人数
	 * @apiSuccess childrenNum 幼儿人数
	 * @apiSuccess customerName 服务商
	 * @apiSuccess customerTel 服务电话
	 * @apiSuccess {String} recipes B早餐BP早加餐D午加餐DP午餐D2晚餐D2P晚加餐
	 * @apiSuccessExample {json} Success-Response:
	  {
	    "id": 1,
	    "versionNo": 1,
	    "provinceId": null,
	    "cityId": null,
	    "districtId": null,
	    "schoolGroupId": null,
	    "name": "某某幼儿园1",
	    "address": "某某省某某市某某区某某路xx号",
	    "linkman": "张先生",
	    "tel": "01088888888",
	    "appScreenImg": null,
	    "logoUrl": null,
	    "inSchoolTime": null,
	    "outSchoolTime": null,
	    "recipeTypes": "B|BP|D|DP|D2|D2P",
	    "cameraOpenHours": null,
	    "smsBalance": null,
	    "isActive": 1,
	    "areaName": "北京市 北京市 东城区",
	    "teacherNum": 25,
	    "parentNum": 7,
	    "childrenNum": 131,
	    "customerName": "杨洋1",
	    "customerTel": "",
	    "versionNo": 1,                版本号，需要上传
	    "recipes": [                   食谱类型
			      "B",
			      "BP",
			      "D",
			      "DP",
			      "D2",
			      "D2P"
			    ]
	  }
	 * 
	 */
	@RequestMapping(value="info",method=RequestMethod.GET)
	public RespJson info(){
		Long schoolId = LoginUtil.getSchoolId();
		SchoolVo school = schoolService.getSchool(schoolId);
		return RespJsonFactory.buildSuccess(school);
	}
	
	
	/**
	 * @api {POST} /school/save 保存学校信息
	 * @apiGroup school
	 * @apiName schoolSave
	 * 
	 * @apiParam address 地址
	 * @apiParam linkman 负责人
	 * @apiParam tel 联系电话
	 * @apiParamExample {json} Request-Example:
	 * {
		  "address": "string",
		  "linkman": "string",
		  "tel": "132",
		  "versionNo": 1
		}
	 * 
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public RespJson save(@RequestBody @Valid SchoolReq schoolreq){
		
		Long schoolId = LoginUtil.getSchoolId();
		School school = new School();
		BeanUtils.copyProperties(schoolreq, school);
		school.setId(schoolId);
		
		schoolService.saveSchool(school);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} /school/saveSetting 保存学校设置
	 * @apiGroup school
	 * @apiName schoolSaveSetting
	 * 
	 * @apiParam {Array} recipes 
	 * 
	 * @apiParam {Array} recipes B早餐BP早加餐D午加餐DP午餐D2晚餐D2P晚加餐
	 * @apiParamExample {json} Request-Example:
	 * {
	  "recipes": [
		      "B",
		      "BP",
		      "D",
		      "DP",
		      "D2",
		      "D2P"
		  ],
		  "versionNo": 4
		}
	 */
	@RequestMapping(value="saveSetting",method=RequestMethod.POST)
	public RespJson saveSetting(@RequestBody @Valid SchoolSetting setting){
		School school = new School();
		Long schoolId = LoginUtil.getSchoolId();
		List<String> recipes = setting.getRecipes();
		String reduce = recipes.stream().reduce("",
				(result,element) -> result = result+"|"+element);
		if(reduce.length()>1){
			reduce = reduce.substring(1);
		}
		school.setId(schoolId);
		school.setVersionNo(setting.getVersionNo());
		school.setRecipeTypes(reduce);
		
		schoolService.saveSetting(school);
		
		return RespJsonFactory.buildSuccess();
	}
	
}
