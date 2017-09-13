package cn.imexue.ec.web.web.controller.xc.xcChild;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.ChildRideExtVo;
import cn.imexue.ec.common.model.vo.ChildRideVo;
import cn.imexue.ec.common.model.vo.IdNameVO;
import cn.imexue.ec.common.model.vo.SchoolChildRideVo;
import cn.imexue.ec.common.model.vo.XcChildVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.xc.xcChild.XcChildManagerService;
import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildQuery;
import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq;
import cn.imexue.ec.web.web.role.Role;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcChild.XcChildManageController.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 校车幼儿管理Controller <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Role
@RestController
@RequestMapping("xcChildManage")
@Api(value = "校车幼儿管理")
public class XcChildManageController {

	@Resource
	private XcChildManagerService	xcChildManagerService;

	/**
	 * @api {POST} /xcChildManage/list 分页获取校车幼儿信息
	 * @apiGroup xcChildManage
	 * @apiName list
	 * @apiParam {String} name 幼儿名称
	 * @apiParam {Long } classId 班级ID
	 * @apiParam {Long } lineId 线路ID
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "pageNo": 0,
	 *                  "name":"吴菲菲",
	 *                  "classId":100045,
	 *                  "lineId": 1
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 20,
	 *                    "lineId": 56,
	 *                    "lineName": "IOS线路",
	 *                    "name": "金正民",
	 *                    "childId": 210857,
	 *                    "sex": 1,
	 *                    "classId": 100045,
	 *                    "className": "太阳一班",
	 *                    "parentVo": [
	 *                    {
	 *                    "id": 7,
	 *                    "versionNo": 1,
	 *                    "mobile": "13776002925",
	 *                    "name": "金三胖",
	 *                    "sex": null,
	 *                    "birthday": null,
	 *                    "logoUrl": "image/2017/04/14/b674b9cc-2092-4e58-a84e-1ba0e5fff8e1.png",
	 *                    "isActive": 1,
	 *                    "relationName": "爸爸",
	 *                    "relationCode": "BB",
	 *                    "children": null,
	 *                    "isDefault": 0
	 *                    }
	 *                    ]
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "校车幼儿管理", notes = "查询校车幼儿")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RespJson list(Page<XcChildVo> page, @RequestBody XcChildQuery query) {

		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(XcChildVo.class);

		xcChildManagerService.pageLists(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {DELETE} /xcChildManage/deleteXcChild/{id} 删除校车幼儿
	 * @apiName deleteXcChild
	 * @apiGroup xcChildManage
	 * @apiParam {Number} Id 校车幼儿ID
	 * @apiDescription 删除校车幼儿
	 */
	@ApiOperation(value = "删除校车幼儿", notes = "删除校车幼儿")
	@RequestMapping(value = "/deleteXcBus/{id}", method = RequestMethod.DELETE)
	public RespJson deleteXcChild(@PathVariable Long id) {

		xcChildManagerService.deleteXcChild(id);
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {POST} /xcChildManage/addXcChild 添加校车幼儿
	 * @apiName addXcChild
	 * @apiGroup xcChildManage
	 * @apiParam {Array} ids 幼儿ID
	 * @apiParam {Long} lineId 线路ID
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "lineId": 7,
	 *                  "ids": [
	 *                  210788,210802
	 *                  ]
	 *                  }
	 * @apiDescription 添加校车
	 */
	@ApiOperation(value = "添加校车幼儿", notes = "添加校车幼儿")
	@RequestMapping(value = "addXcChild", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public RespJson addXcChild(@RequestBody @Valid XcChildReq req) {

		Long schoolId = LoginUtil.getSchoolId();
		req.setSchoolId(schoolId);
		xcChildManagerService.deleteByParam(req);
		xcChildManagerService.SaveXcChild(req);
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {GET} /xcChildManage/findClassInfo 查询当前学校的需要乘车的幼儿班级统计
	 * @apiGroup xcChildManage
	 * @apiName findClassInfo
	 * @apiDescription 查询当前学校的需要乘车的幼儿班级统计
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "childTotal": 1135,
	 *                    "xcClassList": [
	 *                    {
	 *                    "classChildTotal": 1128,
	 *                    "className": "太阳一班",
	 *                    "classId": 100045
	 *                    },
	 *                    {
	 *                    "classChildTotal": 1,
	 *                    "className": "测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试",
	 *                    "classId": 100148
	 *                    },
	 *                    {
	 *                    "classChildTotal": 2,
	 *                    "className": "天海二班",
	 *                    "classId": 100249
	 *                    },
	 *                    {
	 *                    "classChildTotal": 3,
	 *                    "className": "1",
	 *                    "classId": 100459
	 *                    },
	 *                    {
	 *                    "classChildTotal": 1,
	 *                    "className": "终极一班",
	 *                    "classId": 100489
	 *                    }
	 *                    ]
	 *                    }
	 */
	@ApiOperation(value = "查询当前学校的需要乘车的幼儿班级统计", notes = "班级幼儿")
	@RequestMapping(value = "findClassInfo", method = RequestMethod.GET)
	public RespJson findClassInfo() {

		Long schoolId = LoginUtil.getSchoolId();
		ChildRideVo info = xcChildManagerService.findClassBySchoolId(schoolId);
		return RespJsonFactory.buildSuccess(info);
	}

	/**
	 * @api {GET} /xcChildManage/findClassChildInfo/{classIds}/{lineId} 查询当前学校的需要乘车的班级幼儿
	 * @apiGroup xcChildManage
	 * @apiName findClassChildInfo
	 * @apiDescription 查询当前学校的需要乘车的班级幼儿
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 210782,
	 *                    "name": "吴菲菲",
	 *                    "hasExit": 0,
	 *                    "xcChildID": 320
	 *                    },
	 *                    {
	 *                    "id": 210785,
	 *                    "name": "刘",
	 *                    "hasExit": 1,
	 *                    "xcChildID": 276
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "查询当前学校的需要乘车的班级幼儿", notes = "班级幼儿")
	@RequestMapping(value = "findClassChildInfo/{classIds}/{lineId}", method = RequestMethod.GET)
	public RespJson findClassChildInfo(@PathVariable("classIds") String classIds, @PathVariable("lineId") String lineId) {

		Long schoolId = LoginUtil.getSchoolId();
		List<String> classlist = Arrays.asList(classIds.split(","));
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		param.put("lineId", lineId);
		param.put("classIds", classlist);
		List<ChildRideExtVo> info = xcChildManagerService.findChildInfoNeedByParam(param);
		return RespJsonFactory.buildSuccess(info);
	}

	/**
	 * @api {GET} /xcChildManage/findRideChildInfo/{lineId}/{busId} 查询当前学校的已经乘车的班级幼儿
	 * @apiGroup xcChildManage
	 * @apiName findRideChildInfo
	 * @apiDescription 查询当前学校的已经乘车的班级幼儿
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 210782,
	 *                    "name": "吴菲菲",
	 *                    },
	 *                    {
	 *                    "id": 210785,
	 *                    "name": "刘",
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "查询当前学校的已经乘车的班级幼儿", notes = "班级幼儿")
	@RequestMapping(value = "findRideChildInfo", method = RequestMethod.GET)
	public RespJson findRideChildInfo(@RequestParam("lineId") Long lineId, @RequestParam(name = "busId", required = false) Long busId) {

		Long schoolId = LoginUtil.getSchoolId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		param.put("lineId", lineId);
		if (busId != null) {
			param.put("busId", busId);
		}
		List<IdNameVO> info = xcChildManagerService.findChildBaseByParam(param);
		return RespJsonFactory.buildSuccess(info);
	}

	/**
	 * @api {GET} /xcChildManage/findAllChildRideInfo/{lineId} 查询当前学校的是否乘车的幼儿信息
	 * @apiGroup xcChildManage
	 * @apiName findAllChildRideInfo
	 * @apiDescription 查询当前学校的是否乘车的幼儿信息
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "className": "太阳一班",
	 *                    "classId": 100045,
	 *                    "childRideExtVo": [
	 *                    {
	 *                    "id": 210780,
	 *                    "name": "王深深2222",
	 *                    "hasExit": 0,
	 *                    "xcChildID": null
	 *                    },
	 *                    ]
	 */
	@ApiOperation(value = "查询当前学校的是否乘车的幼儿信息", notes = "全校幼儿")
	@RequestMapping(value = "findAllChildRideInfo/{lineId}", method = RequestMethod.GET)
	public RespJson findAllChildRideInfo(@PathVariable("lineId") Long lineId) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lineId", lineId);
		List<SchoolChildRideVo> list = xcChildManagerService.findAllChildByParam(param, LoginUtil.getSchoolId());
		return RespJsonFactory.buildSuccess(list);
	}

}
