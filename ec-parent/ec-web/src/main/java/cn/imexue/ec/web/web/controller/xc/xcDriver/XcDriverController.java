package cn.imexue.ec.web.web.controller.xc.xcDriver;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.XcDriver;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.xc.XcDriverService;
import cn.imexue.ec.web.web.controller.xc.xcDriver.req.XcDriverDelReq;
import cn.imexue.ec.web.web.controller.xc.xcDriver.req.XcDriverQuery;
import cn.imexue.ec.web.web.controller.xc.xcDriver.req.XcDriverReq;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.XcDriverController.java</br>
 * 初始作者： wangshaojie</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 校车司机管理 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (橘子股份-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */

@RestController
@Role(RoleType.ALL)
@RequestMapping("xcDriver")
@Api(value = "校车司机管理")
public class XcDriverController {

	@Resource
	private XcDriverService	xcDriverService;

	/**
	 * @api {POST} /xcDriver/list 分页获取校车司机信息
	 * @apiGroup xcDriver
	 * @apiName list
	 * @apiParam {String} driverName 司机姓名
	 * @apiParam {String} driverTel 司机电话
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "name":"王少杰",
	 *                  "status": "15001782969"
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 1,
	 *                    "driverName": "王少杰",
	 *                    "idNo": "342224111207141522",
	 *                    "licenseNo": "苏EA32067",
	 *                    "driverTel": "15001782969",
	 *                    "attendanceCardNo": "2222222222",
	 *                    "versionNo": 2
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "司机管理", notes = "查询司机")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RespJson list(Page<XcDriver> page, @RequestBody XcDriverQuery query) {

		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(XcDriver.class);
		xcDriverService.pageLists(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {GET} /xcDriver/get/{id} 司机详情
	 * @apiGroup xcDriver
	 * @apiName xcDriverGet
	 * @apiParam {Number} id 司机id
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id":"1111"
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 1,
	 *                    "driverName": "王少杰",
	 *                    "idNo": "342224111207141522",
	 *                    "licenseNo": "苏EA32067",
	 *                    "driverTel": "15001782969",
	 *                    "attendanceCardNo": "2222222222",
	 *                    "versionNo": 2
	 *                    }
	 */
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public RespJson get(@PathVariable("id") Long id) {

		XcDriver xcDriver = xcDriverService.getById(id);

		return RespJsonFactory.buildSuccess(xcDriver);
	}

	/**
	 * @api {POST} /xcDriver/delete 司机删除
	 * @apiGroup xcDriver
	 * @apiName xcDriverDelete
	 * @apiParam {Number} id 司机id
	 * @apiParam {Number} versionNo 版本号
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id":"1111",
	 *                  "versionNo":"2"
	 *                  }
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public RespJson delete(@RequestBody @Valid XcDriverDelReq req) {

		xcDriverService.deleteById(req.getId(), req.getVersionNo());
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {POST} /xcDriver/save 保存司机信息
	 * @apiGroup xcDriver
	 * @apiName xcDriverSave
	 * @apiDescription 如果id不存在则新增，如果id存在则修改
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id": 1,
	 *                  "driverName": "王少杰",
	 *                  "idNo": "342224111207141522",
	 *                  "licenseNo": "苏EA32067",
	 *                  "driverTel": "15001782969",
	 *                  "attendanceCardNo": "2222222222",
	 *                  "licenseNo":"qweqweqw"
	 *                  "versionNo": 2
	 *                  }
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public RespJson save(@RequestBody @Valid XcDriverReq driverReq) {

		XcDriver driver = new XcDriver();
		BeanUtils.copyProperties(driverReq, driver);
		xcDriverService.save(driver);
		return RespJsonFactory.buildSuccess();
	}

}
