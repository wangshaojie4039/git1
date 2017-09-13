package cn.imexue.ec.web.web.controller.device;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.DeviceNvr;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.DeviceNvrVO;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.device.NvrService;
import cn.imexue.ec.web.web.controller.device.req.DeviceAttendanceBaseInfo;
import cn.imexue.ec.web.web.controller.device.req.DeviceReq;
import cn.imexue.ec.web.web.controller.device.req.NvrQuery;
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
@RequestMapping("nvr")
@Role(RoleType.D)
public class NvrController {

    @Resource
    private NvrService nvrService;

    /**
     * @throws Exception
     * @api {POST} /nvr/list 分页获取设备信息
     * @apiGroup nvr
     * @apiName list
     * @apiParam {Byte} deviceStatus 实时状态(0或者null不在线,1在线)
     * @apiParam {String} deviceNo 设备编号
     * @apiSuccess {String} list 参考<a href="#api-nvr-get">get</a>
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
     * @apiSuccess {Date} confirmTime 确认收货时间
     * @apiSuccess {Number} versionNo 版本号
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
    public RespJson list(Page<DeviceNvr> page, @RequestBody NvrQuery query) throws Exception {

	Long schoolId = LoginUtil.getSchoolId();
	if ("deviceNo".equals(query.getOrderBy())) {
	    page.getSearch().put("judge", "judge");
	    page.setOrderBy("deviceno");
	}
	page.getSearch().put("schoolId", schoolId);
	page.checkOrder(DeviceNvr.class);
	nvrService.pageList(page.getSearch());

	return RespJsonFactory.buildSuccess(page);
    }

    /**
     * @api {GET} /nvr/get/{id} 获取设备信息
     * @apiGroup nvr
     * @apiName get
     * @apiPermission
     * @apiParam {Long} id 设备Id
     * @apiSuccess {Number} isDefaultDeviceNo 是否是默认设备，如果为1则不显示deviceNo
     * @apiSuccess {String} imageUrl 图片地址，需要加上前缀域名
     * @apiSuccess {String} deviceSecret 设备秘钥
     * @apiSuccess {Date} confirmTime 订单确认时间
     * @apiSuccess {Number} isActive 是否启用(0不启用1启用)
     * @apiSuccess {String} orderNo 订单号
     * @apiSuccess {Date} installTime 安装到学校的时间
     * @apiSuccess {Number} status 设备状态(0或者null不在线1在线)
     * @apiSuccess {String} imageUrl 图片地址，需要加上前缀域名
     * @apiSuccessExample {json} Success-Response:
     *                    {
     *                    "id": 56,
     *                    "versionNo": 0,
     *                    "customerId": 0,
     *                    "productId": 4,
     *                    "schoolId": 1085,
     *                    "schoolName": null,
     *                    "deviceNo": "34343",
     *                    "isDefaultDeviceNo": 0,
     *                    "deviceSecret": "3434",
     *                    "name": "硬盘录像机",
     *                    "model": "003",
     *                    "imageUrl": "logo/video-record.png",
     *                    "orderNo": "2170510516065875",
     *                    "confirmTime": "2017-05-10 10:56:17",
     *                    "isActive": 1,
     *                    "installTime": null,
     *                    "list": [
     *                    {
     *                    "id": 14,
     *                    "versionNo": 0,
     *                    "customerId": 0,
     *                    "productId": 1,
     *                    "nvrId": 56,
     *                    "schoolId": 1085,
     *                    "schoolName": "苏州天天幼儿园（勿动）",
     *                    "classId": 35,
     *                    "className": "小二班",
     *                    "installLocation": "小二班",
     *                    "deviceNo": "693806348-2",
     *                    "isDefaultDeviceNo": 0,
     *                    "validateCode": "ABCDEF",
     *                    "channelNo": null,
     *                    "name": "啊摄像头<input type=\"text\"/>",
     *                    "model": "007",
     *                    "imageUrl": "",
     *                    "orderNo": "2170316457541251",
     *                    "confirmTime": "2017-03-16 14:12:25",
     *                    "openHours": null,
     *                    "isActive": 1,
     *                    "installTime": "2017-05-15 15:16:33",
     *                    "status": null,
     *                    "nvrName": null,
     *                    "schoolIds": null
     *                    }
     *                    ]
     *                    }
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public RespJson get(@PathVariable("id") Long id) {

	DeviceNvrVO deviceNvrVo = nvrService.getByNvrId(id);
	return RespJsonFactory.buildSuccess(deviceNvrVo);
    }

    /**
     * @api {POST} /nvr/updateSecret 更改设备信息
     * @apiGroup nvr
     * @apiName updateSecret
     * @apiPermission
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

	DeviceNvr device2 = new DeviceNvr();
	// 拷贝同名的值,注意确保没有多余的值
	BeanUtils.copyProperties(device, device2);
	nvrService.save(device2);
	return RespJsonFactory.buildSuccess();
    }

    /**
     * @api {POST} /nvr/getStatus 获取设备的实时状态
     * @apiGroup nvr
     * @apiName getStatus
     * @apiParam {String[]} deviceNos 设备编号
     * @apiParamExample {json} Request-Example:
                        {
    					  "deviceNos": [
    					    "6664391413333"
    					  ]
    					}
     * @apiError 3001 考勤设备秘钥<code>deviceSecret</code>重复
     */
    @RequestMapping(value = "getStatus", method = RequestMethod.POST)
    public RespJson getStatus(@RequestBody StatusReq<String> statusReq) {

	List<String> statusList = nvrService.getStatusByDeviceNo(statusReq);

	return RespJsonFactory.buildSuccess(statusList);
    }

    /**
     * @api {POST} /nvr/forbidDevice 禁用设备
     * @apiGroup nvr
     * @apiName forbidDevice
     * @apiPermission d
     * @apiParam {Long} id 设备id
     * @apiParam {Number} versionNo 版本号
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "id": 1234,
     *                  "versionNo" : 1
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "禁用设备")
    @RequestMapping(value = "forbidDevice", method = RequestMethod.POST)
    public RespJson forbidDevice(@RequestBody DeviceReq deviceReq) {

	Result result = nvrService.forbidDevice(deviceReq.getId(), deviceReq.getVersionNo());
	return RespJsonFactory.buildSuccess(result);
    }

    /**
     * @api {POST} /nvr/startDevice 启用设备
     * @apiGroup nvr
     * @apiName startDevice
     * @apiPermission d
     * @apiParam {Long} id 设备id
     * @apiParam {Number} versionNo 版本号
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "id": 12345,
     *                  "versionNo" : 1
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "启用设备")
    @RequestMapping(value = "startDevice", method = RequestMethod.POST)
    public RespJson startDevice(@RequestBody DeviceReq deviceReq) {

	Result result = nvrService.startDevice(deviceReq.getId(), deviceReq.getVersionNo());
	return RespJsonFactory.buildSuccess(result);
    }
}
