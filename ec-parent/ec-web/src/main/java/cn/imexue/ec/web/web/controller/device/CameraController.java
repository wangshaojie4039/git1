package cn.imexue.ec.web.web.controller.device;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.DeviceCamera;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.Result;
import cn.imexue.ec.web.service.device.CameraService;
import cn.imexue.ec.web.service.school.ClassService;
import cn.imexue.ec.web.web.controller.device.req.CameraQuery;
import cn.imexue.ec.web.web.controller.device.req.DeviceReq;
import cn.imexue.ec.web.web.controller.device.req.PointDeviceReq;
import cn.imexue.ec.web.web.controller.device.req.StatusReq;
import cn.imexue.ec.web.web.role.Role;

/**
 * 考勤设备类
 *
 * @author hl
 */
@Api(value = "考勤设备类")
@RestController
@RequestMapping("camera")
@Role()
public class CameraController {

    @SuppressWarnings("unused")
	private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private CameraService cameraService;

    @Resource
    private ClassService classService;

    /**
     * @throws Exception
     * @api {POST} /camera/list 分页获取设备信息
     * @apiGroup camera
     * @apiName list
     * @apiParam {Byte} isActive 实时状态(0不正常,1正常,不传全部)
     * @apiParam {String} deviceNo 设备编号
     * @apiParam {Long} classId 班级Id
     * @apiSuccess {String} list 参考<a href="#api-camera-get">get</a>
     * @apiSuccess {String} schoolName 学校名称
     * @apiSuccess {Long} classId 班级Id
     * @apiSuccess {String} className 班级名称
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
    public RespJson list(Page<DeviceCamera> page, @RequestBody CameraQuery query) throws Exception {

	Long schoolId = LoginUtil.getSchoolId();

	String role = LoginUtil.getLoginInfo().getUserRole();
	Long teachId = LoginUtil.getLoginId();
	if (role.equals(Constants.APP_USER_ROLE_TEACHER)) {

	    List<Long> classIdLists = classService.getClassListByTeachId(teachId, schoolId);
	    StringBuffer classId = new StringBuffer();
	    if (!classIdLists.isEmpty()) {

		classId.append("AND a.class_id in (  ");
		if (query.getClassId() == null) {
		    for (Long classIdList : classIdLists) {
			classId.append(classIdList + ",");
		    }
		    classId = new StringBuffer(classId.substring(0, classId.length() - 1));
		} else {
		    classId.append(query.getClassId());
		}
		classId.append(")");

	    } else {
		classId.append("AND a.class_id = -100  ");
	    }
	    page.getSearch().put("classIds", classId.toString());

	}

	if ("deviceNo".equals(query.getOrderBy())) {
	    page.getSearch().put("judge", "judge");
	    page.setOrderBy("deviceno");
	}
	page.getSearch().put("schoolId", schoolId);

	page.checkOrder(DeviceCamera.class);
	cameraService.pageList(page.getSearch());

	return RespJsonFactory.buildSuccess(page);
    }

    /**
     * @api {GET} /camera/get/{id} 获取设备信息
     * @apiGroup camera
     * @apiName get
     * @apiParam {Long} id 设备Id
     * @apiSuccess {Number} isDefaultDeviceNo 是否是默认设备编号，如果为1则不显示deviceNo
     * @apiSuccess {String} imageUrl 图片地址，需要加上前缀域名
     * @apiSuccess {String} deviceSecret 设备秘钥
     * @apiSuccess {Date} confirmTime 订单确认时间
     * @apiSuccess {Number} isActive 是否启用(0不启用1启用)
     * @apiSuccess {String} orderNo 订单号
     * @apiSuccess {String} validateCode 验证码model
     * @apiSuccess {String} model 型号
     * @apiSuccess {Date} installTime 安装到学校的时间
     * @apiSuccess {Number} status 设备状态(0或者null不在线1在线)
     * @apiSuccess {String} imageUrl 图片地址，需要加上前缀域名
     * @apiSuccessExample {json} Success-Response:
     *                    {
    					    "id": 12,
    					    "versionNo": 7,
    					    "customerId": 47,
    					    "productId": 1,
    					    "nvrId": 500109,
    					    "schoolId": 1048,
    					    "schoolName": "小英才幼儿园  测试",
    					    "classId": 0,
    					    "className": null,
    					    "installLocation": "就看见打飞机快递费打发地方大幅度3地方非",
    					    "deviceNo": "693806348",
    					    "isDefaultDeviceNo": 0,
    					    "validateCode": "AXEWUK",
    					    "channelNo": null,
    					    "name": "王阳明",
    					    "model": "007",
    					    "imageUrl": "",
    					    "orderNo": "2170316457541251",
    					    "confirmTime": "2017-03-16 14:12:25",
    					    "openHours": "",
    					    "isActive": 1,
    					    "installTime": "2017-06-08 14:30:54",
    					    "status": null,
    					    "nvrName": null,
    					    "schoolIds": null
    					  }
     */
    @RequestMapping(value = "get/{id}", method = RequestMethod.GET)
    public RespJson get(@PathVariable("id") Long id) {

	DeviceCamera deviceCamera = cameraService.getById(id);
	return RespJsonFactory.buildSuccess(deviceCamera);
    }

    /**
     * @api {POST} /camera/updateSecret 更改设备信息
     * @apiGroup camera
     * @apiName updateSecret
     * @apiParam {string[1..20]} name 设备名称
     * @apiParam {Long} id 设备Id
     * @apiParam {string} imageUrl 设备图片
     * @apiParam {Number} versionNo 版本号
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "id": 1,
     *                  "name" : "考勤设备名称",
     *                  "imageUrl"："www"
     *                  "versionNo" : 1
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "更改名称")
    @RequestMapping(value = "updateSecret", method = RequestMethod.POST)
    public RespJson updateSecret(@RequestBody DeviceReq deviceReq) {

	DeviceCamera device2 = new DeviceCamera();
	// 拷贝同名的值,注意确保没有多余的值
	BeanUtils.copyProperties(deviceReq, device2);
	cameraService.save(device2);
	return RespJsonFactory.buildSuccess();
    }

    /**
     * @api {POST} /camera/getStatus 获取设备的实时状态
     * @apiGroup camera
     * @apiName getStatus
     * @apiParam {String[]} deviceNos 设备编号
     * @apiParamExample {json} Request-Example:
                        {
                    	"deviceNos": [
                    	"666439141"
                    		 ]
                	}
     */
    @RequestMapping(value = "getStatus", method = RequestMethod.POST)
    public RespJson getStatus(@RequestBody StatusReq<String> statusReq) {

	List<String> statusList = cameraService.getStatusByDeviceNo(statusReq);

	return RespJsonFactory.buildSuccess(statusList);
    }

    /**
     * @api {POST} /camera/forbidDevice 禁用设备
     * @apiGroup camera
     * @apiName forbidDevice
     * @apiParam {Long} id 设备id
     * @apiParam {Number} versionNo 版本号
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "id": 6612,
     *                  "versionNo" : 1
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "禁用设备")
    @RequestMapping(value = "forbidDevice", method = RequestMethod.POST)
    public RespJson forbidDevice(@RequestBody DeviceReq deviceReq) {

	Result result = cameraService.forbidDevice(deviceReq.getId(), deviceReq.getVersionNo());
	return RespJsonFactory.buildSuccess(result);
    }

    /**
     * @api {POST} /camera/startDevice 启用设备
     * @apiGroup camera
     * @apiName startDevice
     * @apiPermission d
     * @apiParam {Long} id 设备id
     * @apiParam {Number} versionNo 版本号
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "id": 666123,
     *                  "versionNo" : 1
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "启用设备")
    @RequestMapping(value = "startDevice", method = RequestMethod.POST)
    public RespJson startDevice(@RequestBody DeviceReq deviceReq) {

	Result result = cameraService.startDevice(deviceReq.getId(), deviceReq.getVersionNo());
	return RespJsonFactory.buildSuccess(result);
    }

    /**
     * @api {POST} /camera/getClassList 获取班级列表
     * @apiGroup camera
     * @apiName getClassList
     * @apiPermission d
     * @apiParam {Long} schoolId 学校Id
     * @apiSuccess {Long} id 班级Id
     * @apiSuccess {String} name 班级名称
     * @apiSuccessExample {json} Request-Example:
                         {
    					  "result": 1,
    					  "code": null,
    					  "msg": "成功",
    					  "data":
    					  [
    						    {
    						      "id": 1,
    						      "versionNo": 1,
    						      "schoolId": 1,
    						      "name": "小一班14",
    						      "schoolYear": "2017",
    						      "masterTeacherId": 2,
    						      "lastPromoteTime": "2017-02-21 17:09:07",
    						      "isGraduate": null,
    						      "graduateTime": null,
    						      "isDelete": null,
    						      "deleteTime": null,
    						      "childNum": null,
    						      "parentNum": null,
    						      "master": null,
    						      "teachers": null
    						    },
    					  	]
    					}

     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @RequestMapping(value = "getClassList", method = RequestMethod.POST)
    public RespJson getClassList(@RequestBody DeviceReq deviceReq) {

	List<ClassVo> classVos = cameraService.getClassBySchoolId(deviceReq.getSchoolId());

	return RespJsonFactory.buildSuccess(classVos);
    }

    /**
     * @api {POST} /camera/pointDevice 分配设备
     * @apiGroup camera
     * @apiName pointDevice
     * @apiParam {Long} id 设备id
     * @apiParam {Long} classId 班级Id(如果设备分配的到班级就传值)
     * @apiParam {Number} versionNo 版本号
     * @apiParam {String} installLocation 设备安装位置
     * @apiParamExample {json} Request-Example:
     *                  {
     *                  "id": "1",
     *                  "classId": "1",
     *                  "versionNo" : 1,
     *                  installLocation:"班级/。。。"
     *                  }
     * @apiError 3001 考勤设备编号<code>deviceNo</code>重复
     */
    @ApiOperation(value = "更改设备信息", notes = "启用设备")
    @RequestMapping(value = "pointDevice", method = RequestMethod.POST)
    public RespJson pointDevice(@RequestBody PointDeviceReq deviceReq) {

	Result result = cameraService.updateDevice(deviceReq);
	return RespJsonFactory.buildSuccess(result);
    }

    public static void main(String[] args) {

	System.out.println("h".getBytes()[0]);
	System.out.println(new String(new byte[] { -84, -19, 0, 5, 116, 0, 20, 104, 105, 107, 58, 54, 54, 54, 52, 51, 57, 49, 52, 49, 58, 115, 116, 97, 116, 117, 115 }));
    }
}
