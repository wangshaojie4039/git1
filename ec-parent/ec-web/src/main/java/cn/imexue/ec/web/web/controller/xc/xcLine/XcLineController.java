package cn.imexue.ec.web.web.controller.xc.xcLine;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.XcLine;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.XcLineBusVo;
import cn.imexue.ec.common.model.vo.XcLineItemVo;
import cn.imexue.ec.common.model.vo.XcLineVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.HttpUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.xc.xcChild.XcChildManagerService;
import cn.imexue.ec.web.service.xc.xcLine.XcLineManagerService;
import cn.imexue.ec.web.web.controller.xc.xcChild.req.XcChildReq;
import cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineChangeReq;
import cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineQuery;
import cn.imexue.ec.web.web.controller.xc.xcLine.req.XcLineReq;
import cn.imexue.ec.web.web.role.Role;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.xc.xcLine.XcLineController.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年7月18日<br/>
 * 功能说明： 校车线路Controller <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */

@Role()
@RestController
@RequestMapping("xcLineManage")
@Api(value = "校车线路管理")
public class XcLineController {

	@Resource
	private XcLineManagerService xcLineManagerService;

	@Resource
	private XcChildManagerService xcChildManagerService;

	@Value("${baidu.yinyan.url.delete}")
	private String delete;

	@Value("${baidu.yinyan.url.add}")
	private String add;
	@Value("${baidu.yinyan.url.update}")
	private String update;

	@Value("${baidu.yinyan.ak}")
	private String ak;

	@Value("${baidu.yinyan.serviceId}")
	private int service_id;

	/**
	 * @api {POST} /xcLineManage/list 分页获取校车线路信息
	 * @apiGroup xcLineManage
	 * @apiName list
	 * @apiParam {String} lineName 线路名称
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "lineName":"车坊线",
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 1,
	 *                    "versionNo": 1,
	 *                    "schoolId": null,
	 *                    "lineName": "乐桥线",
	 *                    "driverId": 1,
	 *                    "teacherId": 99,
	 *                    "plateNumber": "苏EA32067",
	 *                    "nuclearSeating": 20,
	 *                    "childCount": 7
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "校车线路儿列表", notes = "查询线路")
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RespJson list(Page<XcLineVo> page, @RequestBody XcLineQuery query) {

		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(XcLineVo.class);
		xcLineManagerService.pageLists(page.getSearch());
		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {POST} /xcLineManage/deleteXcLine 删除校车线路
	 * @apiName deleteXcLine
	 * @apiGroup xcLineManage
	 * @apiParam {Number} Id 校车幼儿ID
	 * @apiParam {Number} versionNo 版本号
	 * @apiDescription 删除校车线路
	 */
	@ApiOperation(value = "删除校车线路", notes = "删除校车线路")
	@RequestMapping(value = "/deleteXcLine", method = RequestMethod.POST)
	public RespJson deleteXcLine(@RequestBody XcLineChangeReq req) {

		xcLineManagerService.deleteXcLine(req.getId(), req.getVersionNo());
		postBaiduYinyan(delete, req.getId() + "", "", null);
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {POST} /xcLineManage/addXcLine 添加校车线路
	 * @apiName addXcLine
	 * @apiGroup xcLineManage
	 * @apiParam {XcBusChangeReq} id 校车ID,versionNo版本号
	 * @apiParam {String} lineName 线路名称
	 * @apiParam {Array} xcLineChildReq 校车幼儿
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "xcBusChangeReq": {
	 *                  "id":2,
	 *                  "versionNo":3
	 *                  },
	 *                  "lineName": "Android线路",
	 *                  }
	 * @apiDescription 添加校车线路
	 */
	@ApiOperation(value = "添加校车线路", notes = "添加校车线路")
	@RequestMapping(value = "/addXcLine", method = RequestMethod.POST)
	public RespJson addXcLine(@RequestBody @Valid XcLineReq req) {

		Long schoolId = LoginUtil.getSchoolId();
		req.setSchoolId(schoolId);
		XcLine x = xcLineManagerService.saveXcLine(req);

		postBaiduYinyan(add, x.getId() + "", req.getLineName(), schoolId + "");
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {PUT} /xcLineManage/updateXcLine/{id} 修改校车线路
	 * @apiName updateXcLine
	 * @apiGroup xcLineManage
	 * @apiParam {XcBusChangeReq} id 校车ID,versionNo版本号
	 * @apiParam {String} lineName 线路名称
	 * @apiParam {Array} xcLineChildReq 校车幼儿
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "lineName": "梅花仙鹿",
	 *                  "versionNo": 0,
	 *                  "xcBusChangeReq": {
	 *                  "id": 1082,
	 *                  "versionNo": 1
	 *                  },
	 *                  "ids": [
	 *                  210788,210802
	 *                  ]
	 *                  }
	 *                  }
	 * @apiDescription 修改校车线路
	 */
	@ApiOperation(value = "修改校车线路", notes = "修改校车线路")
	@RequestMapping(value = "/updateXcLine/{id}", method = RequestMethod.PUT)
	public RespJson updateXcLine(@PathVariable Long id, @RequestBody XcLineReq req) {

		Long schoolId = LoginUtil.getSchoolId();
		req.setSchoolId(schoolId);
		req.setId(id);
		if (null != req.getIds() && req.getIds().size() > 0) {
			XcChildReq reqs = new XcChildReq();
			reqs.setIds(req.getIds());
			reqs.setLineId(id);
			reqs.setSchoolId(schoolId);
			xcChildManagerService.deleteByParam(reqs);

		}
		xcLineManagerService.updateXcLine(req);
		postBaiduYinyan(update, req.getId() + "", req.getLineName(), schoolId + "");
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {GET} /xcLineManage/getXcLine/{id} 查询该线路详情
	 * @apiGroup xcLineManage
	 * @apiName getXcLine
	 * @apiDescription 查询该线路详情
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 1,
	 *                    "lineName": "乐桥线",
	 *                    "xcBusItemVo": {
	 *                    "id": 1,
	 *                    "model": "audi",
	 *                    "plateNumber": "苏EA32067",
	 *                    "nuclearSeating": 10
	 *                    }
	 *                    }
	 */
	@ApiOperation(value = "查询该线路详情", notes = "线路详情")
	@RequestMapping(value = "getXcLine/{id}", method = RequestMethod.GET)
	public RespJson getXcLine(@PathVariable Long id) {

		Long schoolId = LoginUtil.getSchoolId();
		XcLineItemVo vo = xcLineManagerService.getXcLine(schoolId, id);
		return RespJsonFactory.buildSuccess(vo);
	}

	/**
	 * @api {GET} /xcLineManage/findAllLine 查询当前学校的全部线路
	 * @apiGroup xcLineManage
	 * @apiName findAllLine
	 * @apiDescription 查询当前学校的全部线路
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 2055,
	 *                    "versionNo": 4,
	 *                    "schoolId": 1048,
	 *                    "lineName": "梅花仙鹿",
	 *                    "plateNumber": "dbad29",
	 *                    "nuclearSeating": 49,
	 *                    "childCount": 3
	 *                    },
	 *                    {
	 *                    "id": 2053,
	 *                    "versionNo": 0,
	 *                    "schoolId": 1048,
	 *                    "lineName": "刘翔线路",
	 *                    "plateNumber": null,
	 *                    "nuclearSeating": null,
	 *                    "childCount": 0
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "查询当前学校的全部线路", notes = "所有线路")
	@RequestMapping(value = "findAllLine", method = RequestMethod.GET)
	public RespJson findAllLine() {

		Long schoolId = LoginUtil.getSchoolId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		param.put("orderBy", "id");
		param.put("orderType", "desc");
		List<? extends XcLineVo> list = xcLineManagerService.pageLists(param);
		return RespJsonFactory.buildSuccess(list);
	}

	/**
	 * @api {GET} /xcLineManage/findAllUsefulLine 查询当前学校的可绑定线路
	 * @apiGroup xcLineManage
	 * @apiName findAllUsefulLine
	 * @apiParam {Long} lineId 线路ID
	 * @apiDescription 查询当前学校的可绑定线路
	 * @apiSuccessExample {json} Success-Response:
	 *                    [
	 *                    {
	 *                    "id": 2,
	 *                    "versionNo": 0,
	 *                    "schoolId": 1048,
	 *                    "lineName": "车坊-官渎里立交",
	 *                    },
	 *                    {
	 *                    "id": 8,
	 *                    "versionNo": 0,
	 *                    "schoolId": 1048,
	 *                    "lineName": "xx线路"
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "查询当前学校的可绑定线路", notes = "所有线路")
	@RequestMapping(value = "findAllUsefulLine", method = RequestMethod.GET)
	public RespJson findAllUsefulLine(@RequestParam(required = false) Long lineId) {

		Long schoolId = LoginUtil.getSchoolId();
		List<XcLineBusVo> teacherlist = xcLineManagerService.findAllBySchoolId(schoolId);
		if (lineId != null) {
			XcLine line = xcLineManagerService.getById(lineId);
			XcLineBusVo vo = new XcLineBusVo();
			vo.setLineName(line.getLineName());
			vo.setId(line.getId());
			vo.setVersionNo(line.getVersionNo());
			vo.setSchoolId(line.getSchoolId());
			teacherlist.add(vo);
		}

		return RespJsonFactory.buildSuccess(teacherlist);
	}

	/**
	 * @api {GET} /xcLineManage/validateLineName/{lineName} 校验线路名称是否重复
	 * @apiGroup xcLineManage
	 * @apiName validateLineName
	 * @apiParam {Long} id 线路ID(修改必填)
	 * @apiDescription 校验线路名称是否重复
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "result": 0,
	 *                    "code": null,
	 *                    "msg": "该线路名称已存在！",
	 *                    "data": null
	 *                    }
	 */
	@ApiOperation(value = "校验线路名称是否重复", notes = "所有线路")
	@RequestMapping(value = "validateLineName/{lineName}", method = RequestMethod.GET)
	public RespJson validateName(@PathVariable String lineName, @RequestParam(required = false) Long id) {

		Long schoolId = LoginUtil.getSchoolId();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("schoolId", schoolId);
		param.put("lineName", lineName);
		if (id != null) {
			param.put("id", id);
		}

		RespJson result = xcLineManagerService.validateName(param);

		return result;
	}

	private void postBaiduYinyan(String type, String lineId, String lineName, String schoolId) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("ak", ak);
		map.put("service_id", service_id + "");
		map.put("entity_name", lineId);
		map.put("line_name", lineName);
		if (schoolId != null) {
			map.put("school_id", schoolId);
		}
		HttpUtil.post(type, "utf-8", map);

	}
}
