package cn.imexue.ec.web.web.controller.device;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.DeviceAttendance;
import cn.imexue.ec.common.model.common.KeyValuePair;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.DeviceAttendanceVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.device.AttendanceService;
import cn.imexue.ec.web.web.controller.device.req.AttendanceQuery;
import cn.imexue.ec.web.web.controller.device.req.DeviceAttendanceBaseInfo;
import cn.imexue.ec.web.web.controller.device.req.DeviceReq;
import cn.imexue.ec.web.web.controller.device.req.StatusReq;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

/**
 * 考勤设备类
 *
 * @author hl
 */
@Api(value = "考勤设备类")
@RestController
@RequestMapping("attentance")
@Role(RoleType.D)
public class AttendanceController {

	@Resource
	private AttendanceService attendanceService;

	/**
	 * @throws Exception
	 * @api {POST} /attentance/list 分页获取设备信息
	 * @apiGroup attentance
	 * @apiName list
	 * @apiParam {Byte} deviceStatus 实时状态(0或者null不在线,1在线)
	 * @apiParam {String} deviceNo 设备编号
	 * @apiParam {String} productType 设备类型（校车考勤机(BUS_ATTENDANCE)或者考勤机(ATTENDANCE)）
	 * @apiSuccess {String} list 参考<a href="#api-camera-get">get</a>
	 * @apiSuccess {String} schoolName 学校名称
	 * @apiSuccess {String} deviceNo 设备编号
	 * @apiSuccess {Byte} isDefaultDeviceNo 设备秘钥是否修改过
	 * @apiSuccess {String} deviceSecret 设备秘钥
	 * @apiSuccess {String} name 考勤机的命名
	 * @apiSuccess {String} model 考勤机型号
	 * @apiSuccess {String} imageUrl 设备图片地址
	 * @apiSuccess {Byte} isActive 设备状态(0不启用1启用)
	 * @apiSuccess {Byte} status 实时状态(0或者null不在线,1在线)
	 * @apiSuccess {String} productName 设备名称
	 * @apiSuccess {String} productTypeStr 考勤机的类型
	 * @apiSuccess {Date} confirmTime 确认收货时间
	 * @apiSuccess {Number} versionNo 版本号
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "isActive": 1,
	 *                  "deviceNo": "666123456",
	 *                  }
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 7,
	 *                    "versionNo": 0,
	 *                    "customerId": null,
	 *                    "productId": null,
	 *                    "schoolId": 1,
	 *                    "schoolName": "苏州橘智幼儿园",
	 *                    "deviceNo": "gs001",
	 *                    "isDefaultDeviceNo": null,
	 *                    "deviceSecret": "bu9xev7u",
	 *                    "name": "考勤1",
	 *                    "model": "009",
	 *                    "imageUrl": "image/2017/3/13/fa315198-57a2-40f9-866c-f196cbf19965.jpg",
	 *                    "orderNo": "2170316918760443",
	 *                    "confirmTime": "2017-03-16 17:26:31",
	 *                    "isActive": 1,
	 *                    "installTime": null,
	 *                    "status": null,
	 *                    "productName": "考勤机",
	 *                    "productTypeStr":"校车考勤机",
	 *                    "provinceId": null,
	 *                    "cityId": null,
	 *                    "districtId": null,
	 *                    "provinceName": null,
	 *                    "cityName": null,
	 *                    "districtName": null,
	 *                    "customerName": null,
	 *                    "customerMobile": null
	 *                    },
	 */
	@RequestMapping(value = "list", method = RequestMethod.POST)
	public RespJson list(Page<DeviceAttendanceVo> page, @RequestBody AttendanceQuery query) throws Exception {

		Long schoolId = LoginUtil.getSchoolId();
		if ("deviceNo".equals(query.getOrderBy())) {
			page.getSearch().put("judge", "judge");
			page.setOrderBy("deviceno");
		}
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(DeviceAttendanceVo.class);
		attendanceService.pageList(page.getSearch());

		return RespJsonFactory.buildSuccess(page);
	}

	@RequestMapping(value = "busList", method = RequestMethod.POST)
	public RespJson busList(Page<DeviceAttendanceVo> page, @RequestBody AttendanceQuery query) throws Exception {

		Long schoolId = LoginUtil.getSchoolId();
		if ("deviceNo".equals(query.getOrderBy())) {
			page.getSearch().put("judge", "judge");
			page.setOrderBy("deviceno");
		}
		page.getSearch().put("productType", "BUS_ATTENDANCE");
		page.getSearch().put("schoolId", schoolId);
		page.checkOrder(DeviceAttendanceVo.class);
		attendanceService.pageList(page.getSearch());

		return RespJsonFactory.buildSuccess(page);
	}

	/**
	 * @api {GET} /attentance/get 获取设备信息
	 * @apiGroup attentance
	 * @apiName get
	 * @apiParam {Long} id 设备Id
	 * @apiParam {String} deviceNo 设备编号
	 * @apiSuccess {Number} isDefaultDeviceNo 是否是默认设备，如果为1则不显示deviceNo
	 * @apiSuccess {String} imageUrl 图片地址，需要加上前缀域名
	 * @apiSuccess {String} deviceSecret 设备秘钥
	 * @apiSuccess {Date} confirmTime 订单确认时间
	 * @apiSuccess {Number} isActive 是否启用(0不启用1启用)
	 * @apiSuccess {String} orderNo 订单号
	 * @apiSuccess {Date} installTime 安装到学校的时间
	 * @apiSuccess {Number} status 实时状态(0或者null不在线1在线)
	 * @apiSuccess {String} imageUrl 图片地址，需要加上前缀域名
	 * @apiSuccessExample {json} Success-Response:
	 *                    {
	 *                    "id": 3,
	 *                    "versionNo": 1,
	 *                    "customerId": 1,
	 *                    "productId": 3,
	 *                    "schoolId": 0,
	 *                    "schoolName": "某某幼儿园1",
	 *                    "deviceNo": "4234",
	 *                    "isDefaultDeviceNo": 0,
	 *                    "deviceSecret": "j039atv9",
	 *                    "name": "都是",
	 *                    "model": "mm2",
	 *                    "imageUrl": "/image/2017/3/6/74e7f7c3-498a-463e-ac70-e2487c6fad45.jpg",
	 *                    "orderNo": "2170223140537186",
	 *                    "confirmTime": "2017-03-07 17:05:55",
	 *                    "isActive": 1,
	 *                    "installTime": "2017-05-04 21:15:31",
	 *                    "status": null,
	 *                    "productName": null,
	 *                    "provinceId": null,
	 *                    "cityId": null,
	 *                    "districtId": null,
	 *                    "provinceName": null,
	 *                    "cityName": null,
	 *                    "districtName": null,
	 *                    "customerName": null,
	 *                    "customerMobile": null
	 *                    }
	 */
	@RequestMapping(value = "get", method = RequestMethod.GET)
	public RespJson get(Long id, String deviceNo) {

		DeviceAttendance attendance = attendanceService.getByIdOrDeviceNo(id, deviceNo);
		return RespJsonFactory.buildSuccess(attendance);
	}

	/**
	 * @api {POST} /attentance/updateSecret 更改设备信息
	 * @apiGroup attentance
	 * @apiName updateSecret
	 * @apiParam {string[1..20]} name 设备名称
	 * @apiParam {Long} id 设备Id
	 * @apiParam {Number} versionNo 版本号
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id": 1,
	 *                  "deviceNo": "666123456",
	 *                  "deviceSecret": "qwerty",
	 *                  "name" : "考勤设备名称",
	 *                  "versionNo" : 1
	 *                  }
	 * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
	 */
	@ApiOperation(value = "更改设备信息", notes = "更改名称")
	@RequestMapping(value = "updateSecret", method = RequestMethod.POST)
	public RespJson updateSecret(@RequestBody DeviceAttendanceBaseInfo device) {

		DeviceAttendance device2 = new DeviceAttendance();
		// 拷贝同名的值,注意确保没有多余的值
		BeanUtils.copyProperties(device, device2);
		attendanceService.save(device2);
		return RespJsonFactory.buildSuccess();
	}

	/**
	 * @api {POST} /attentance/getStatus 获取设备的实时状态
	 * @apiGroup attentance
	 * @apiName getStatus
	 * @apiParam {String[]} deviceNo 设备编号
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "deviceNos": [
	 *                  "6664391413333"
	 *                  ]
	 *                  }
	 * @apiError 3001 考勤设备秘钥<code>deviceSecret</code>重复
	 */
	@RequestMapping(value = "getStatus", method = RequestMethod.POST)
	public RespJson getStatus(@RequestBody StatusReq<String> statusReq) {

		List<String> statusList = attendanceService.getStatusByDeviceNo(statusReq);

		return RespJsonFactory.buildSuccess(statusList);
	}

	/**
	 * @api {POST} /attentance/forbidDevice 禁用设备
	 * @apiGroup attentance
	 * @apiName forbidDevice
	 * @apiParam {Long} id 设备id
	 * @apiParam {Number} versionNo 版本号
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id": 66612345,
	 *                  "versionNo" : 1
	 *                  }
	 * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
	 */
	@ApiOperation(value = "更改设备信息", notes = "禁用设备")
	@RequestMapping(value = "forbidDevice", method = RequestMethod.POST)
	public RespJson forbidDevice(@RequestBody DeviceReq deviceReq) {

		Result result = attendanceService.forbidDevice(deviceReq.getId(), deviceReq.getVersionNo());
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {POST} /attentance/startDevice 启用设备
	 * @apiGroup attentance
	 * @apiName startDevice
	 * @apiPermission d
	 * @apiParam {Long} id 设备id
	 * @apiParam {Number} versionNo 版本号
	 * @apiParamExample {json} Request-Example:
	 *                  {
	 *                  "id": "666123456",
	 *                  "versionNo" : 1
	 *                  }
	 * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
	 */
	@ApiOperation(value = "更改设备信息", notes = "启用设备")
	@RequestMapping(value = "startDevice", method = RequestMethod.POST)
	public RespJson startDevice(@RequestBody DeviceReq deviceReq) {

		Result result = attendanceService.startDevice(deviceReq.getId(), deviceReq.getVersionNo());
		return RespJsonFactory.buildSuccess(result);
	}

	/**
	 * @api {GET} /attentance/findBusDeviceAttendance 获取校车考勤机key-value
	 * @apiGroup attentance
	 * @apiName findBusDeviceAttendance
	 * @apiSuccessExample {json} Success-Response:[
	 *                    {
	 *                    "key": "xc",
	 *                    "value": "考勤机xc"
	 *                    }
	 *                    ]
	 */
	@ApiOperation(value = "获取校车考勤机key-value", notes = "校车考勤机")
	@RequestMapping(value = "findBusDeviceAttendance", method = RequestMethod.GET)
	public RespJson findBusDeviceAttendance() {

		Long schoolId = LoginUtil.getSchoolId();
		List<KeyValuePair> teacherlist = attendanceService.findBusDeviceAttendance(schoolId);
		return RespJsonFactory.buildSuccess(teacherlist);
	}
}
