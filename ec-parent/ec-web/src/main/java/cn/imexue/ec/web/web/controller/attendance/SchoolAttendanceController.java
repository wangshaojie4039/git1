package cn.imexue.ec.web.web.controller.attendance;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.vo.ChildAttendanceDto;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.service.sys.SysCodeService;
import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.api.model.AttendanceSchoolModel;
import cn.imexue.ec.web.model.mongo.ChildAttendanceClassHistory;
import cn.imexue.ec.web.model.mongo.ChildAttendanceHistory;
import cn.imexue.ec.web.service.attendance.school.SchoolAttendanceService;
import cn.imexue.ec.web.web.controller.attendance.req.SchoolAttendQuery;
import cn.imexue.ec.web.web.controller.attendance.req.TodayAttendanceDetailQuery;
import cn.imexue.ec.web.web.excel.ExcelExportUtil;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@Role
@RestController
@RequestMapping("school/attendance")
public class SchoolAttendanceController {

	@Resource
	private SchoolAttendanceService schoolAttendanceService;
	
	@Resource
	private SysCodeService sysCodeService;
	
	/**
	 * @api {POST} /school/attendance/history 班级历史考勤
	 * @apiGroup schoolAttendance
	 * @apiName history
	 * 
	 * @apiSuccessExample  {json} Success-Response:
	 * {参考校园历史考勤}
	 * 
	 */
	@RequestMapping(value="history",method=RequestMethod.POST)
	public RespJson history(@RequestBody @Valid SchoolAttendQuery req){
		Date start = req.getStart();
		Date end = req.getEnd();
		long between = DateUtil.getBetween(start, end);
		if(between>92){
			throw new RuntimeException("时间跨度过大");
		}
		
		Long classId = req.getClassId();
		Long schoolId = LoginUtil.getSchoolId();
		
		
		List<ChildAttendanceHistory> list = schoolAttendanceService.getHistoryClassAttendance(schoolId, classId, start, end);
		Map<String, Object> map = new HashMap<>();
		list.sort((x,y)->x.getDate().compareTo(y.getDate()));
		if(Integer.MAX_VALUE!=req.getPageSize()){
			int max = (req.getPageNo())*req.getPageSize();
			list = list.subList((req.getPageNo()-1)*req.getPageSize(),list.size()>max?max: list.size());
		}
		
		map.put("list", list);
		map.put("total", list.size());
		
		return RespJsonFactory.buildSuccess(map);
	}
	
	/**
	 * @api {POST} /school/attendance/schoolHistory 学校历史考勤
	 * @apiGroup schoolAttendance
	 * @apiName schoolHistory
	 * 
	 * @apiParam {Date} start 查询的开始日期
	 * @apiParam {Date} end 查询的结束日期
	 * 
	 * @apiSuccessExample  {json} Success-Response:
	 * {
		"total": 1,
		"list": [
		  {
		    "id": {
		      "timestamp": 1499153486,
		      "machineIdentifier": 9755696,
		      "processIdentifier": 3060,
		      "counter": 13169447,
		      "date": "2017-07-04 15:31:26",
		      "time": 1499153486000,
		      "timeSecond": 1499153486
		    },
		    "schoolId": 1048,
		    "date": "2017-07-03 15:31:18",
		    "classId": null,
		    "normals": [],
		    "absenteeisms": [
		      {
		        "classId": 100045,
		        "childName": "吴小飞",
		        "logo": "image/2017/4/14/6becf75d-55c5-4058-b919-7d852c0bf6c7.jpg",
		        "inTime": null,
		        "inStatus": null,
		        "inReason": null,
		        "outStatus": null,
		        "outReason": null,
		        "outTime": null
		      },
		      ..
		      ]
		     }
		   }
	 */
	@Role(RoleType.D)
	@RequestMapping(value="schoolHistory",method=RequestMethod.POST)
	public RespJson schoolHistory(@RequestBody @Valid SchoolAttendQuery req){
		req.setClassId(null);
		
		return history(req);
	}
	
	/**
	 * @api {GET} /school/attendance/today 学校当日考勤
	 * @apiGroup schoolAttendance
	 * @apiName today
	 * 
	 * @apiSuccess {JsonArray} attendIns 出勤信息
	 * @apiSuccess {JsonArray} attendOuts 退勤信息
	 * @apiSuccess {String} attendInTitle 出勤/退勤主题
	 * @apiSuccess {String} attendOutTitle 出勤/退勤主题 
	 * @apiSuccess {Integer} totalCnt 班级总人数
	 * @apiSuccess {Integer} attendCnt 出勤/退勤数
	 * @apiSuccess {String} rateTitle 出勤/退勤率显示标题
	 * @apiSuccess {String} cntTitle 出勤/退勤数显示标题
	 * @apiSuccessExample {json} Success-Response:
	 *   HTTP/1.1 200 OK
	 *   {
	 *       "code": 200,
	 *       "msg": "ok",
	 *       "attendIns": [
	 *          {
	 *              "attendCnt": 9,
	 *              "rateTitle": "出勤率",
	 *              "cntTitle": "出勤数"
	 *          },
	 *          {
	 *              "attendCnt": 1,
	 *              "rateTitle": "缺勤率",
	 *              "cntTitle": "缺勤数"
	 *          }
	 *       ],
	 *       "attendOuts": [
	 *          {
	 *              "attendCnt": 10,
	 *              "rateTitle": "出勤率",
	 *              "cntTitle": "出勤数"
	 *          },
	 *          {
	 *              "attendCnt": 0,
	 *              "rateTitle": "缺勤率",
	 *              "cntTitle": "缺勤数"
	 *          }
	 *       ],
	 *       "attendInTitle": "全园签到考勤",
	 *       "attendOutTitle": "全园签退考勤",
	 *       "totalCnt": 10
	 *   }
	 */
	@Role(RoleType.D)
	@RequestMapping(value="today",method=RequestMethod.GET)
	public RespJson today(){
		
		Long loginId = LoginUtil.getLoginId();
		Long schoolId = LoginUtil.getSchoolId();
		
		Map<String, Object> school = schoolAttendanceService.school(schoolId, loginId);
		return RespJsonFactory.buildSuccess(school);
	}
	
	
	/**
	 * @api {GET} /school/attendance/class/today/{id} 班级当日考勤
	 * @apiGroup schoolAttendance
	 * @apiName todayClass
	 * 
	 * @apiSuccess {String} id 班级ID
	 * 
	 * @apiSuccess {JsonArray} attendIns 出勤信息
	 * @apiSuccess {JsonArray} attendOuts 退勤信息
	 * @apiSuccess {String} attendInTitle 出勤/退勤主题
	 * @apiSuccess {String} attendOutTitle 出勤/退勤主题 
	 * @apiSuccess {Integer} totalCnt 班级总人数
	 * @apiSuccess {Integer} attendCnt 出勤/退勤数
	 * @apiSuccess {String} rateTitle 出勤/退勤率显示标题
	 * @apiSuccess {String} cntTitle 出勤/退勤数显示标题
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 *   HTTP/1.1 200 OK
	 *   {
	 *       "code": 200,
	 *       "msg": "ok",
	 *       "attendIns": [
	 *          {
	 *              "attendCnt": 9,
	 *              "rateTitle": "出勤率",
	 *              "cntTitle": "出勤数"
	 *          },
	 *          {
	 *              "attendCnt": 1,
	 *              "rateTitle": "缺勤率",
	 *              "cntTitle": "缺勤数"
	 *          }
	 *       ],
	 *       "attendOuts": [
	 *          {
	 *              "attendCnt": 10,
	 *              "rateTitle": "出勤率",
	 *              "cntTitle": "出勤数"
	 *          },
	 *          {
	 *              "attendCnt": 0,
	 *              "rateTitle": "缺勤率",
	 *              "cntTitle": "缺勤数"
	 *          }
	 *       ],
	 *       "attendInTitle": "小一班签到考勤",
	 *       "attendOutTitle": "小一班签退考勤",
	 *       "totalCnt": 10
	 *   }
	 */
	@RequestMapping(value="class/today/{id}",method=RequestMethod.GET)
	public RespJson today(@PathVariable("id")Long classId){
		
		Long loginId = LoginUtil.getLoginId();
		Long schoolId = LoginUtil.getSchoolId();
		
		Map<String, Object> school = schoolAttendanceService.clazz(schoolId, loginId, classId);
		return RespJsonFactory.buildSuccess(school);
	}
	
	/**
	 * @api {POST} /school/attendance/todayDetail 班级当日明细
	 * @apiGroup schoolAttendance
	 * @apiName todayDetail
	 * 
	 * 
	 * @apiParamExample {json} Request-Example:
	 *   {
	 *       "classId":1,
	 *       "attendDiv":"1",
	 *       "inOutDiv":"in"
	 *   }
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 *   HTTP/1.1 200 OK
	 *   [
	 *           {
	 *               "childId":1,
	 *               "childName":"天天",
	 *               "status":1,
	 *               "leaveType":"",
	 *               "leaveName":""
	 *           },
	 *           {
	 *               "childId":2,
	 *               "childName":"乐乐",
	 *               "status":"3",
	 *               "leaveType":"BL",
	 *               "leaveName":"事假"
	 *           }
	 */
	@RequestMapping(value="todayDetail",method=RequestMethod.POST)
	public RespJson todayDetail(@RequestBody TodayAttendanceDetailQuery query){
		Assert.notNull(query.getClassId(),"classId不能为空");
		Assert.notNull(query.getAttendDiv(), "出入园区分不能为空");
		if(query.getAttendDiv() != null){
			Assert.state(query.getAttendDiv().matches("(0|1){1}"), "出入园区分错误");
		}
		boolean inDiv = "in".equalsIgnoreCase(query.getInOrOut());
		
		Long loginId = LoginUtil.getLoginId();
		Long schoolId = LoginUtil.getSchoolId();
		
		List<ChildAttendanceDto> list = schoolAttendanceService.classDetail(loginId,
				query.getClassId(), schoolId, query.getAttendDiv(),inDiv);
		
		int max = (query.getPageNo())*query.getPageSize();
		List<ChildAttendanceDto> list2 = list.subList((query.getPageNo()-1)*query.getPageSize(),list.size()>max?max: list.size());
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("list", list2);
		map.put("total", list.size());
		
		return RespJsonFactory.buildSuccess(map);
	}
	
	/**
	 * @api {POST} /school/attendance/school/todayDetail 学校当日明细
	 * @apiGroup schoolAttendance
	 * @apiName schoolTodayDetail
	 * 
	 * @apiParam {String} attendDiv 打卡区分（1：已打卡 0:未打卡）
	 * 
	 * @apiParamExample {json} Request-Example:
	 *   {
	 *       "attendDiv":"1",
	 *   }
	 * 
	 * 
	 * @apiSuccess {Long} childId 孩子ID
	 * @apiSuccess {String} childName 孩子姓名
	 * @apiSuccess {String} childLogo 孩子图像
	 * @apiSuccess {Integer} inStatus 孩子出勤退勤状态（1：正常 2或者null：迟到/早退 3：请假 4:忘打卡】）
	 * @apiSuccess {String} outReason 入园/出园 请假类型名称（事假/病假/其他原因）
	 * @apiSuccessExample {json} Success-Response:
	 *   HTTP/1.1 200 OK
	 *   total:20,
	 *    "list":[
		    {
		      "classId": 100045,
		      "childName": "吴小飞",
		      "logo": "image/2017/4/14/6becf75d-55c5-4058-b919-7d852c0bf6c7.jpg",
		      "inTime": null,
		      "inStatus": null,
		      "inReason": null,
		      "outStatus": null,
		      "outReason": null,
		      "outTime": null
		    },
		    {
		      "classId": 100045,
		      "childName": "吴菲菲",
		      "logo": "image/2017/4/14/cbe1cef0-f076-4a54-8b01-25cf42c5255e.jpg",
		      "inTime": null,
		      "inStatus": null,
		      "inReason": null,
		      "outStatus": null,
		      "outReason": null,
		      "outTime": null
		    },
		    ..
		    ]
	 *   
	 */
	@Role(RoleType.D)
	@RequestMapping(value="school/todayDetail",method=RequestMethod.POST)
	public RespJson schoolTodayDetail(@RequestBody TodayAttendanceDetailQuery query){
		
		Assert.notNull(query.getAttendDiv(), "打卡区分不能为空");
		Assert.notNull(query.getPageNo(),"query.getPageNo()不能为空");
		Assert.notNull(query.getPageSize(),"query.getPageNo()不能为空");
		if(query.getAttendDiv() != null){
			Assert.state(query.getAttendDiv().matches("(0|1){1}"), "出入园区分错误");
		}
		String inOrOut = query.getInOrOut();
//		if(inOrOut!=null){
//			Assert.state(inOrOut.matches("(in|out){1}"), "出入园区分错误");
//		}
		Long loginId = LoginUtil.getLoginId();
		Long schoolId = LoginUtil.getSchoolId();
		
		boolean inDiv = "in".equalsIgnoreCase(inOrOut);
		
		List<ChildAttendanceDto> list = schoolAttendanceService.schoolDetail(loginId, schoolId, query.getAttendDiv(),inDiv);
		int max = (query.getPageNo())*query.getPageSize();
		List<ChildAttendanceDto> list2 = list.subList((query.getPageNo()-1)*query.getPageSize(),list.size()>max?max: list.size());
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("list", list2);
		map.put("total", list.size());
		
		return RespJsonFactory.buildSuccess(map);
	}
	
	/**
	 * @api {POST} /school/attendance/setting 设置
	 * @apiGroup schoolAttendance
	 * @apiName setting
	 * 
	 * 
	 * @apiParam {Long} childId 孩子ID
	 * @apiParam {String} inOutDiv 出勤退勤区分（in：出勤 out:退勤）
	 * @apiParam {String} leaveType 请假类型（BL事假/SL病假/OTHER其他）
	 * @apiParam {String} leaveReason 请假原因
	 * 
	 * @apiParamExample {json} Request-Example:
	 *   参考校园明细
	 */
	@RequestMapping(value="setting",method=RequestMethod.POST)
	public RespJson setting(@RequestBody @Valid AttendanceSchoolModel model){
		
		Long loginId = LoginUtil.getLoginId();
		Long schoolId = LoginUtil.getSchoolId();
		model.setSchoolId(schoolId);
		String leaveType = model.getLeaveType();
		String leaveReason = null;
		Assert.notNull(leaveType,"leaveType不能为null");
		switch(leaveType){
		case SysCodeService.LEAVE_TYPE_BL:
			leaveReason="病假";
			break;
		case SysCodeService.LEAVE_TYPE_SL:
			leaveReason="事假";
			break;
		default:
			leaveReason = model.getLeaveReason();
		}
		model.setLeaveReason(leaveReason);
		schoolAttendanceService.update(loginId, model);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} /school/attendance/export/school 导出学校历史考勤
	 * @apiGroup schoolAttendance
	 * @apiName exportSchool
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="export/school",method=RequestMethod.GET)
	public void exportSchool(HttpServletResponse response,@Valid SchoolAttendQuery req){
		req.setClassId(null);
		req.setPageSize(Integer.MAX_VALUE);
		RespJson respJson = history(req);
		List list = (List) ((Map)respJson.getData()).get("list");
		ExcelExportUtil.export("学校历史考勤.xls", list, response, ChildAttendanceHistory.class);
		
	}
	
	/**
	 * @api {GET} /school/attendance/export/class 导出班级历史考勤
	 * @apiGroup schoolAttendance
	 * @apiName exportClass
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="export/class",method=RequestMethod.GET)
	public void exportClass(HttpServletResponse response,@Valid SchoolAttendQuery req){
		
		req.setPageSize(Integer.MAX_VALUE);
		RespJson respJson = history(req);
		List list = (List) ((Map)respJson.getData()).get("list");
		ExcelExportUtil.export("班级历史考勤.xls", list, response, ChildAttendanceClassHistory.class);
		
	}
}
