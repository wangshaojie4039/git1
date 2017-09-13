package cn.imexue.ec.web.web.controller.school;

import io.swagger.annotations.Api;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.DynamicVO;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.school.DynamicService;
import cn.imexue.ec.web.web.controller.school.req.DynamicQuery;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@Api
@RestController
@Role(RoleType.ALL)
@RequestMapping("dynamic")
public class DynamicController {

	@Resource
	private DynamicService dynamicService;
	
	/**
	 * @api {POST} /dynamic/list 动态列表
	 * @apiGroup dynamic
	 * @apiName dynamicList
	 * 
	 * @apiParam {Long[classId]} classId 班级
	 * @apiParam {String[userInfo]} userInfo 发布者
	 * @apiParam {String[content]} content 内容
	 * @apiParam {Date[startTime]} startTime 起始日期
	 * @apiParam {Date[endTime]} endTime 结束日期
	 * 
	 * 
	 * @apiSuccess {Number} id 动态id
	 * @apiSuccess {String}content 动态内容
	 * @apiSuccess {String} dynamicType text-纯文本类型(显示) image -文本图片类型([图片]文本) video -视频类型([视频])
	 * @apiSuccess {String} userInfo 发布者信息(用户名  手机号)
	 * @apiSuccess {String[]} classNames 动态所在班级列表
	 * @apiSuccess {Number} isDirector 是否是园长发的动态(对于园长发的动态，老师角色只能看，不能操作，隐藏删除按钮)
	 * @apiSuccess {String} userInfo 发布者信息(用户名  手机号)
	 * @apiSuccess {Number} commentCount 评论数
	 * @apiSuccess {Number} likeCount 点赞数
	 * @apiSuccess {Date} createTime 发布时间
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * {
        "id": 106,
        "versionNo": 1,
        "content": "我的幼儿邦第一个动态",
        "dynamicType": "文本类型",
        "userInfo": 王少杰   15001782969,
        "commentCount": "50",
        "likeCount": 50,
        "createTime": 1992-07-14 10:00,
        classNames: [
          "太阳一班",
          "太阳三班",],
        isDirector: 1
      },
	 * 
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public RespJson list(Page<DynamicVO> page,@RequestBody DynamicQuery query){
		page.checkOrder(DynamicVO.class);
		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(DynamicVO.class);
		dynamicService.pageList(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}
	
	
	/**
	 * @api {GET} /dynamic/get/{id} 动态详细
	 * @apiGroup dynamic
	 * @apiName dynamicGet
	 * 
	 */
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public RespJson get(@PathVariable("id") Long id){
		
		DynamicVO dynamic=dynamicService.getById(id);
		
		return RespJsonFactory.buildSuccess(dynamic);
	}
	
	
	/**
	 * @api {GET} /dynamic/delete/{id} 动态删除
	 * @apiGroup dynamic
	 * @apiName dynamicDelete
	 * 
	 */
	@RequestMapping(value="delete/{id}",method=RequestMethod.GET)
	public RespJson delete(@PathVariable("id")Long id){
		dynamicService.deleteById(id);
		return RespJsonFactory.buildSuccess();
	}
}
