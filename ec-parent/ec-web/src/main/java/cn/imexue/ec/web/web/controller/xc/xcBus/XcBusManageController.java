package cn.imexue.ec.web.web.controller.xc.xcBus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.XcBus;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.XcBusVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.xc.xcBus.XcBusManagerService;
import cn.imexue.ec.web.web.controller.xc.xcBus.req.XcBusChangeReq;
import cn.imexue.ec.web.web.controller.xc.xcBus.req.XcBusQuery;
import cn.imexue.ec.web.web.controller.xc.xcBus.req.XcBusReq;
import cn.imexue.ec.web.web.role.Role;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.car.xcBusManageController.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年7月7日</br>
 * 功能说明： 校车管理Controller <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Role
@RestController
@RequestMapping("xcBusManage")
@Api(value = "校车管理")
public class XcBusManageController {

	@Resource
	private XcBusManagerService	xcBusManagerService;

	/**
	 * @api {POST} /xcBusManage/list 分页获取校车信息
	 * @apiGroup xcBusManage
	 * @apiName list
	 * @apiParam {String} plateNumber 车牌号
	 * @apiParam {Byte} status 状态
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "plateNumber":"苏EA32067",
	 *                  "status": "0"
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 1,
	 *                    "schoolId": 1048,
	 *                    "model": "audi",
	 *                    "plateNumber": "苏EA32067",
	 *                    "nuclearSeating": 20,
	 *                    "attendanceDeviceNo": "xc",
	 *                    "attendanceDeviceName": "考勤机xc",
	 *                    "cameraDeviceNo":231
	 *                    "status": 0
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "校车管理", notes = "查询校车")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RespJson list(Page<XcBusVo> page, @RequestBody XcBusQuery query) {

		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(XcBusVo.class);

		xcBusManagerService.pageLists(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {POST} /xcBusManage/deleteXcBus 删除校车
	 * @apiName deleteSchoolNotice
	 * @apiGroup xcBusManage
	 * @apiParam {Number} Id 校车ID
	 * @apiParam {Number} versionNo 版本号
	 * @apiDescription 删除校车
	 */
	@ApiOperation(value = "更改校车状态", notes = "删除校车")
	@RequestMapping(value = "/deleteXcBus", method = RequestMethod.POST)
	public RespJson deleteXcBus(@RequestBody @Valid XcBusChangeReq req) {

		Result result = xcBusManagerService.changeXcBus(req.getId(), req.getVersionNo(), 1);
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {POST} /xcBusManage/stopXcBus 停用校车
	 * @apiName stopXcBus
	 * @apiGroup xcBusManage
	 * @apiParam {Number} Id 校车ID
	 * @apiParam {Number} versionNo 版本号
	 * @apiDescription 删除校车
	 */
	@ApiOperation(value = "更改校车状态", notes = "停用校车")
	@RequestMapping(value = "/stopXcBus", method = RequestMethod.POST)
	public RespJson stopXcBus(@RequestBody @Valid XcBusChangeReq req) {

		Result result = xcBusManagerService.changeXcBus(req.getId(), req.getVersionNo(), 2);
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {POST} /xcBusManage/startXcBus 启用校车
	 * @apiName startXcBus
	 * @apiGroup xcBusManage
	 * @apiParam {Number} Id 校车ID
	 * @apiParam {Number} versionNo 版本号
	 * @apiDescription 启用校车
	 */
	@ApiOperation(value = "更改校车状态", notes = "启用校车")
	@RequestMapping(value = "/startXcBus", method = RequestMethod.POST)
	public RespJson startXcBus(@RequestBody @Valid XcBusChangeReq req) {

		Result result = xcBusManagerService.changeXcBus(req.getId(), req.getVersionNo(), 3);
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {POST} /xcBusManage/addXcBus 添加校车
	 * @apiName addXcBus
	 * @apiGroup xcBusManage
	 * @apiParam {String} model 车辆型号
	 * @apiParam {String} plateNumber 车牌
	 * @apiParam {Number} nuclearSeating 核载人数
	 * @apiParam {String} attendanceDeviceNo 考勤机编号
	 * @apiParam {Number} lineId 线路id
	 * @apiParam {Number} status 状态：0-使用中；1-停用
	 * @apiError 5001 该用户ID不存在
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "attendanceDeviceNo": "@324",
	 *                  "cameraDeviceNo": "sf1",
	 *                  "model": "dsf1",
	 *                  "nuclearSeating": 123,
	 *                  "plateNumber": "string",
	 *                  "status": "1",
	 *                  "versionNo": 4,
	 *                  "lineId": 1
	 *                  }
	 * @apiDescription 添加校车
	 */
	@ApiOperation(value = "更改校车状态", notes = "添加校车")
	@RequestMapping(value = "/addXcBus", method = RequestMethod.POST)
	public RespJson addXcBus(@RequestBody @Valid XcBusReq req) {

		Long schoolId = LoginUtil.getSchoolId();
		req.setSchoolId(schoolId);
		Result result = xcBusManagerService.SaveOrUpdateXcBus(req, 1);
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {PUT} /xcBusManage/updateXcBus/{id} 修改校车
	 * @apiName updateXcBus
	 * @apiGroup xcBusManage
	 * @apiParam {String} model 车辆型号
	 * @apiParam {String} plateNumber 车牌
	 * @apiParam {Number} nuclearSeating 核载人数
	 * @apiParam {Number} status 状态：0-使用中；1-停用
	 * @apiParam {String} attendanceDeviceNo 校车考勤机编号
	 * @apiParam {Number} lineId 线路id
	 * @apiError 5001 该用户ID不存在
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "attendanceDeviceNo": "@324",
	 *                  "cameraDeviceNo": "sf1",
	 *                  "model": "dsf1",
	 *                  "nuclearSeating": 123,
	 *                  "plateNumber": "string",
	 *                  "status": "1",
	 *                  "versionNo": 4,
	 *                  "lineId": 1
	 *                  }
	 * @apiDescription 修改校车
	 */
	@ApiOperation(value = "更改校车状态", notes = "修改校车")
	@RequestMapping(value = "/updateXcBus/{id}", method = RequestMethod.PUT)
	public RespJson updateXcBus(@PathVariable("id") Long id, @RequestBody @Valid XcBusReq req) {

		Assert.notNull(id, "班级Id不能为空");
		Assert.notNull(req.getVersionNo(), "版本号不能为空");
		Long schoolId = LoginUtil.getSchoolId();
		req.setSchoolId(schoolId);
		req.setId(id);
		Result result = xcBusManagerService.SaveOrUpdateXcBus(req, 2);
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {GET} /xcBusManage/findAllXcBus 查询当前学校可用的所有校车
	 * @apiName findAllXcBus
	 * @apiGroup xcBusManage
	 * @apiParamExample {json} Request-Example:
	 *                  [
	 *                  {
	 *                  "id": 1048,
	 *                  "schoolId": 1048,
	 *                  "model": "奥迪",
	 *                  "plateNumber": "dbad996",
	 *                  "nuclearSeating": 35,
	 *                  "attendanceDeviceNo": "d3996",
	 *                  "attendanceDeviceName": null,
	 *                  "cameraDeviceNo": null,
	 *                  "versionNo": 1,
	 *                  "status": 0
	 *                  },
	 *                  {
	 *                  "id": 1047,
	 *                  "schoolId": 1048,
	 *                  "model": "奥迪",
	 *                  "plateNumber": "dbad995",
	 *                  "nuclearSeating": 35,
	 *                  "attendanceDeviceNo": "d3995",
	 *                  "attendanceDeviceName": null,
	 *                  "cameraDeviceNo": null,
	 *                  "versionNo": 1,
	 *                  "status": 0
	 *                  }]
	 * @apiDescription 查询当前学校可用的所有校车
	 */
	@ApiOperation(value = "查询当前学校可用的所有校车", notes = "校车查询")
	@RequestMapping(value = "findAllXcBus", method = RequestMethod.GET)
	public RespJson findAllXcBus() {

		Long schoolId = LoginUtil.getSchoolId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		param.put("status", 0);
		List<XcBusVo> info = xcBusManagerService.findAllXcBus(param);
		return RespJsonFactory.buildSuccess(info);
	}

	/**
	 * @api {GET} /xcBusManage/findFreeXcBus 查询当前学未安排至线路的车辆
	 * @apiName findFreeXcBus
	 * @apiGroup xcBusManage
	 * @apiSuccessExample {json} Response-Example:
	 *                    [
	 *                    {
	 *                    "id": 1048,
	 *                    "schoolId": 1048,
	 *                    "model": "奥迪",
	 *                    "plateNumber": "dbad996",
	 *                    "nuclearSeating": 35,
	 *                    "attendanceDeviceNo": "d3996",
	 *                    "attendanceDeviceName": null,
	 *                    "cameraDeviceNo": null,
	 *                    "versionNo": 1,
	 *                    "status": 0
	 *                    },
	 *                    {
	 *                    "id": 1047,
	 *                    "schoolId": 1048,
	 *                    "model": "奥迪",
	 *                    "plateNumber": "dbad995",
	 *                    "nuclearSeating": 35,
	 *                    "attendanceDeviceNo": "d3995",
	 *                    "attendanceDeviceName": null,
	 *                    "cameraDeviceNo": null,
	 *                    "versionNo": 1,
	 *                    "status": 0
	 *                    }]
	 * @apiDescription 查询当前学校可用的所有校车
	 */
	@ApiOperation(value = "查询当前学未安排至线路的车辆", notes = "校车查询")
	@RequestMapping(value = "findFreeXcBus", method = RequestMethod.GET)
	public RespJson findFreeXcBus() {

		Long schoolId = LoginUtil.getSchoolId();
		List<XcBusVo> info = xcBusManagerService.findFreeXcBus(schoolId);
		return RespJsonFactory.buildSuccess(info);
	}

	/**
	 * @api {GET} /xcBusManage/findXcBusById/{id} 查询当前校车
	 * @apiName findXcBusById
	 * @apiGroup xcBusManage
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id": 1052,
	 *                  "versionNo": 1,
	 *                  "schoolId": 7,
	 *                  "model": "东方德意志",
	 *                  "plateNumber": "日21213",
	 *                  "nuclearSeating": 56,
	 *                  "attendanceDeviceNo": "校车编号2",
	 *                  "cameraDeviceNo": null,
	 *                  "status": 0,
	 *                  "lineId": 43
	 *                  }
	 * @apiDescription 查询当前校车
	 */
	@ApiOperation(value = "查询当前校车", notes = "校车查询")
	@RequestMapping(value = "findXcBusById/{id}", method = RequestMethod.GET)
	public RespJson findXcBusById(@PathVariable("id") Long id) {

		XcBus info = xcBusManagerService.findXcBusById(id);
		return RespJsonFactory.buildSuccess(info);
	}

}
