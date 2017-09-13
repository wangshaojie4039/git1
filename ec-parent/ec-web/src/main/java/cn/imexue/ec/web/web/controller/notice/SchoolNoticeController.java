package cn.imexue.ec.web.web.controller.notice;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.ClassInfoVo;
import cn.imexue.ec.common.model.vo.SchoolNoticeItemVo;
import cn.imexue.ec.common.model.vo.SchoolNoticeVo;
import cn.imexue.ec.common.model.vo.TeacherInfoVo;
import cn.imexue.ec.common.model.vo.TeacherTransVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.api.SchoolNoticeApi;
import cn.imexue.ec.web.api.model.NoticeSendApi;
import cn.imexue.ec.web.service.notice.SchoolNoticeService;
import cn.imexue.ec.web.service.school.ClassService;
import cn.imexue.ec.web.service.school.TeacherService;
import cn.imexue.ec.web.web.controller.notice.req.DeleteReq;
import cn.imexue.ec.web.web.controller.notice.req.SchoolNoticeQuery;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.notice.NoticeController.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年6月26日</br>
 * 功能说明： 通知Controller <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */

@Api(value = "学校通知")
@RestController
@RequestMapping("schoolNotice")
@Role
public class SchoolNoticeController {

	@Resource
	private SchoolNoticeService	schoolNoticeService;

	/**
	 * 老师Service
	 */
	@Resource
	private TeacherService		teacherService;

	/**
	 * 班级Service
	 */
	@Resource
	private ClassService		classService;

	@Resource
	private SchoolNoticeApi		schoolNoticeApi;

	/**
	 * @api {POST} /schoolNotice/out/list 园长/老师分页获取通知信息(发信箱)
	 * @apiGroup schoolNotice
	 * @apiName listOut
	 * @apiParam {String} title 标题
	 * @apiParam {Date} startDate 开始时间
	 * @apiParam {Date} endDate 结束时间
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "title": "标题",
	 *                  "startDate": "2017-07-03",
	 *                  "endDate": "2017-07-10"
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 1590,
	 *                    "createTime": "2017-07-03 15:57:23",
	 *                    "versionNo": null,
	 *                    "schoolId": 14,
	 *                    "classId": 0,
	 *                    "isToAll": 0,
	 *                    "title": "徐丽莎，标题",
	 *                    "content": "徐丽莎胜利徐",
	 *                    "teacherId": 7,
	 *                    "totalClass": 3,
	 *                    "readNum": 0,
	 *                    "totalNum": 1
	 *                    },
	 */
	@RequestMapping(value = "out/list", method = RequestMethod.POST)
	public RespJson list(Page<SchoolNoticeVo> page, @RequestBody SchoolNoticeQuery query) {

		Long schoolId = LoginUtil.getSchoolId();

		Long userId = LoginUtil.getLoginId();

		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(SchoolNoticeVo.class);
		page.getSearch().put("teacherId", userId);
		LoginUtil.getLoginInfo().getUserRole();
		schoolNoticeService.pageList(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {POST} /schoolNotice/in/list 园长/老师分页获取通知信息(收信箱)
	 * @apiGroup schoolNotice
	 * @apiName listIn
	 * @apiParam {String} title 标题
	 * @apiParam {Date} startDate 开始时间
	 * @apiParam {Date} endDate 结束时间
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "title": "标题",
	 *                  "startDate": "2017-07-03",
	 *                  "endDate": "2017-07-10"
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 1590,
	 *                    "createTime": "2017-07-03 15:57:23",
	 *                    "versionNo": null,
	 *                    "schoolId": 14,
	 *                    "classId": 0,
	 *                    "isToAll": 0,
	 *                    "title": "徐丽莎，标题",
	 *                    "content": "徐丽莎胜利徐",
	 *                    "teacherId": 7,
	 *                    "totalClass": 3,
	 *                    "readNum": 0,
	 *                    "totalNum": 1
	 *                    },
	 */
	@RequestMapping(value = "in/list", method = RequestMethod.POST)
	public RespJson listIn(Page<SchoolNoticeVo> page, @RequestBody SchoolNoticeQuery query) {

		Long schoolId = LoginUtil.getSchoolId();

		Long userId = LoginUtil.getLoginId();
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(SchoolNoticeVo.class);
		page.getSearch().put("receiverId", userId);
		LoginUtil.getLoginInfo().getUserRole();
		schoolNoticeService.pageList(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {POST} /schoolNotice/deleteSchoolNotice 删除通知
	 * @apiName deleteSchoolNotice
	 * @apiGroup schoolNotice
	 * @apiParam {Number} Id 通知ID
	 * @apiParam {Number} versionNo 版本号
	 * @apiDescription 删除通知
	 */
	@ApiOperation(value = "更改通知信息", notes = "删除通知")
	@RequestMapping(value = "/deleteSchoolNotice", method = RequestMethod.POST)
	public RespJson deleteSchoolNotice(@RequestBody @Valid DeleteReq req) {

		schoolNoticeService.deleteSchoolNotice(req.getId(), req.getVersionNo());
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {GET} /schoolNotice/getSchoolNotice 查询通知信息
	 * @apiGroup schoolNotice
	 * @apiName getSchoolNotice
	 * @apiParam {Number} id 通知ID
	 * @apiDescription 查询通知信息
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 1,
	 *                    "createTime": "2017-03-07 14:14:57",
	 *                    "versionNo": 1,
	 *                    "schoolId": 1,
	 *                    "classId": 0,
	 *                    "isToAll": 0,
	 *                    "title": "啊哟哟哟",
	 *                    "content": "吆喝了解了",
	 *                    "teacherId": 7,
	 *                    "teacherVo": {
	 *                    "id": 99,
	 *                    "createTime": "2017-03-21 19:37:52",
	 *                    "versionNo": 1,
	 *                    "mobile": "13912766783",
	 *                    "uid": "T13912766783",
	 *                    "uuid": null,
	 *                    "name": "姜鹏雨",
	 *                    "sex": 1,
	 *                    "birthday": null,
	 *                    "hasLogin": 1,
	 *                    "isActive": 1,
	 *                    "logoUrl": "image/2017/04/16/4fe53e02-c683-4e98-8cb1-302da0758ac8.png",
	 *                    "role": "T",
	 *                    "schoolId": 1048,
	 *                    "classes": null,
	 *                    "className": null,
	 *                    "isMaster": null
	 *                    },,
	 *                    "school": {
	 *                    "id": 1,
	 *                    "versionNo": 1,
	 *                    "provinceId": null,
	 *                    "cityId": null,
	 *                    "districtId": null,
	 *                    "schoolGroupId": null,
	 *                    "name": "苏州橘智幼儿园",
	 *                    "address": "姑苏",
	 *                    "linkman": "橘子",
	 *                    "tel": "13000000003",
	 *                    "appScreenImg": null,
	 *                    "logoUrl": null,
	 *                    "inSchoolTime": null,
	 *                    "outSchoolTime": null,
	 *                    "recipeTypes": "B|BP|D|DP|D2|D2P",
	 *                    "cameraOpenHours": null,
	 *                    "smsBalance": null,
	 *                    "isActive": 1
	 *                    },
	 *                    "hasReadSchoolNoticeReceiver": [
	 *                    {
	 *                    "id": 1,
	 *                    "schoolId": 1,
	 *                    "schoolNoticeId": 1,
	 *                    "receiverRole": "P",
	 *                    "receiverId": 5,
	 *                    "receiverName": "崔梦飞",
	 *                    "isRead": 1,
	 *                    "createTime": "2017-03-07 15:54:21",
	 *                    "mobile": null
	 *                    }
	 *                    ],
	 *                    "notReadSchoolNoticeReceiver": [{
	 *                    "id": 1,
	 *                    "schoolId": 1,
	 *                    "schoolNoticeId": 1,
	 *                    "receiverRole": "P",
	 *                    "receiverId": 5,
	 *                    "receiverName": "崔梦飞",
	 *                    "isRead": 1,
	 *                    "createTime": "2017-03-07 15:54:21",
	 *                    "mobile": null
	 *                    }]
	 *                    }
	 */
	@ApiOperation(value = "查询通知信息", notes = "查看通知")
	@RequestMapping(value = "getSchoolNotice/{id}", method = RequestMethod.GET)
	public RespJson schoolNotice(@PathVariable("id") Long id) {

		Long userId = LoginUtil.getLoginId();
		Long schoolId = LoginUtil.getSchoolId();
		schoolNoticeService.updateSchoolNoticeReceiver(schoolId, id, userId, LoginUtil.getLoginInfo().getRole());

		SchoolNoticeItemVo attendance = schoolNoticeService.getNoticeItemById(id);
		return RespJsonFactory.buildSuccess(attendance);
	}

	/**
	 * @api {GET} /schoolNotice/getSchoolNoticeClass 查询接受通知的班级
	 * @apiGroup schoolNotice
	 * @apiName getSchoolNoticeClass
	 * @apiDescription 查询接受通知的班级
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 1,
	 *                    "name": "小一班14"
	 *                    },
	 *                    {
	 *                    "id": 29,
	 *                    "name": "小一班"
	 *                    },
	 *                    {
	 *                    "id": 30,
	 *                    "name": "小二班"
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "查询接受通知的班级", notes = "所有班级")
	@RequestMapping(value = "getSchoolNoticeClass", method = RequestMethod.GET)
	public RespJson getSchoolNoticeClass() {

		Long schoolId = LoginUtil.getSchoolId();
		List<ClassInfoVo> classlist = classService.findAllBySchoolId(schoolId);
		return RespJsonFactory.buildSuccess(classlist);
	}

	/**
	 * @api {GET} /schoolNotice/getSchoolNoticeTeacher 查询接受通知的老师
	 * @apiGroup schoolNotice
	 * @apiName getSchoolNoticeTeacher
	 * @apiDescription 查询接受通知的老师（园长）
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 7,
	 *                    "name": "你是才子",
	 *                    "mobile": "18512525713"
	 *                    },
	 *                    {
	 *                    "id": 28,
	 *                    "name": "访问服务   666",
	 *                    "mobile": "15824569521"
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "查询接受通知的老师", notes = "所有老师")
	@RequestMapping(value = "getSchoolNoticeTeacher", method = RequestMethod.GET)
	@Role(RoleType.D)
	public RespJson getSchoolNoticeTeacher() {

		Long schoolId = LoginUtil.getSchoolId();
		String mobile = LoginUtil.getLoginInfo().getLoginId();
		List<TeacherInfoVo> teacherlist = teacherService.findAllBySchoolId(schoolId, mobile);
		return RespJsonFactory.buildSuccess(teacherlist);
	}

	/**
	 * @api {POST} /schoolNotice/send 发布通知
	 * @apiGroup schoolNotice
	 * @apiName send
	 * @apiParam {Array} classIds 班级ID集合
	 * @apiParam {Long} schoolId 学校ID
	 * @apiParam {Array} teacherIds 老师ID集合 通知指定的老师ID数组，如：["T3302","T3301"]
	 * @apiParam {String} title 标题
	 * @apiParam {String} content 内容
	 * @apiDescription 发布通知
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "result": 1,
	 *                    "code": null,
	 *                    "msg": "成功",
	 *                    "data": {
	 *                    "code": 200,
	 *                    "msg": "ok"
	 *                    }
	 *                    }
	 */
	@ApiOperation(value = "通知信息", notes = "发布通知")
	@RequestMapping(value = "send", method = RequestMethod.POST)
	public RespJson send(@RequestBody @Valid NoticeSendApi api) {

		Long loginId = LoginUtil.getLoginId();
		TeacherTransVo v = teacherService.findUserById(loginId);
		Long schoolId = LoginUtil.getSchoolId();
		api.setSchoolId(schoolId);
		Map<String, Object> map2 = schoolNoticeApi.send(v.getUid() + "|" + v.getPassword(), api);
		return RespJsonFactory.buildSuccess(map2);
	}

}
