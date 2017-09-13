package cn.imexue.ec.web.web.controller.xc;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.mongo.XcChildAttendance;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.web.model.mongo.XcAttendanceStatistics;
import cn.imexue.ec.web.service.xc.XcAttendanceService;
import cn.imexue.ec.web.web.controller.xc.req.XcAttendanceRecordReq;
import cn.imexue.ec.web.web.controller.xc.resp.XcChildAttendanceExcelResp;
import cn.imexue.ec.web.web.excel.ExcelExportUtil;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@Role(RoleType.D)
@RestController
@RequestMapping("xc/attendance")
public class XcAttendanceController {

	@Resource
	private XcAttendanceService xcAttendanceService;

	/**
	 * @api {POST} /xc/attendance/record 校车考勤记录
	 * @apiGroup xcAttendance
	 * @apiName xcAttendanceRecord
	 * 
	 * @apiParam {String} childName
	 * @apiParam {String} plateNumber
	 * @apiParam {String} classId
	 * @apiParam {String} swipeStart
	 * @apiParam {String} swipeEnd
	 * @apiParam {String} lineId
	 * @apiParam {String} attType
	 * 
	 * @apiSuccessExample {json} Success-Response: [ { "id": { "timestamp":
	 *                    1501030969, "machineIdentifier": 2830738,
	 *                    "processIdentifier": -7512, "counter": 11923391,
	 *                    "date": "2017-07-26 09:02:49", "time": 1501030969000,
	 *                    "timeSecond": 1501030969 }, "schoolId": 1048,
	 *                    "cardNo": "3462573811", "classId": 100045,
	 *                    "className": "太阳一班", "userId": 210801, "userName":
	 *                    "胡盈芝", "sex": null, "attDay": "2017-07-26",
	 *                    "swipeTime": "2017-07-26 09:01:19", "fileName":
	 *                    "image/2017/07/26/49de9287-a2d1-49b3-9483-8fa0f430ac3f.jpg"
	 *                    , "userType": "C", "createTime":
	 *                    "2017-07-26 09:02:48", "plateNumber": "苏EA32067",
	 *                    "lineId": 1, "lineName": "乐桥线", "driverId": 1,
	 *                    "driverName": "李刚", "driverTel": "13799999999",
	 *                    "attType": 1, "lineStatusRecordId": { "timestamp":
	 *                    1501030808, "machineIdentifier": 2830738,
	 *                    "processIdentifier": -7512, "counter": 11923380,
	 *                    "date": "2017-07-26 09:00:08", "time": 1501030808000,
	 *                    "timeSecond": 1501030808 }, "teacherCheck": null }]
	 * 
	 */
	@RequestMapping(value = "record", method = RequestMethod.POST)
	public RespJson record(@RequestBody XcAttendanceRecordReq req) {

		Date swipeStart = req.getSwipeStart();
		Date swipeEnd = req.getSwipeEnd();

		List<XcChildAttendance> record = xcAttendanceService.attendanceRecord(
				req.getChildName(), req.getPlateNumber(), req.getClassId(),
				swipeStart, swipeEnd, req.getLineId(), req.getAttType());

		int max = (req.getPageNo()) * req.getPageSize();
		List<XcChildAttendance> list = record.subList((req.getPageNo() - 1)
				* req.getPageSize(), record.size() > max ? max : record.size());

		Map<String, Object> map = new HashMap<>();

		map.put("list", list);
		map.put("total", record.size());

		return RespJsonFactory.buildSuccess(map);
	}
	
	@RequestMapping(value="export/record",method=RequestMethod.GET)
	public void exportRecord(HttpServletResponse response,XcAttendanceRecordReq req){
		
		Date swipeStart = req.getSwipeStart();
		Date swipeEnd = req.getSwipeEnd();

		List<XcChildAttendance> record = xcAttendanceService.attendanceRecord(
				req.getChildName(), req.getPlateNumber(), req.getClassId(),
				swipeStart, swipeEnd, req.getLineId(), req.getAttType());
		
		List<XcChildAttendanceExcelResp> list = new ArrayList<>();
		record.forEach(x->{
			XcChildAttendanceExcelResp resp = new XcChildAttendanceExcelResp();
			BeanUtils.copyProperties(x, resp);
			resp.setAttType(x.getAttType());
			list.add(resp);
		});
		
		ExcelExportUtil.export("校车考勤记录.xls", list, response, XcChildAttendanceExcelResp.class);
		
	}

	/**
	 * @api {POST} /xc/attendance/statistics 校车考勤统计
	 * @apiGroup xcAttendance
	 * @apiName xcAttendanceStatistics
	 */
	@RequestMapping(value="statistics",method=RequestMethod.POST)
	public RespJson statistics(@RequestBody XcAttendanceRecordReq req) throws ParseException{
		Date swipeStart = req.getSwipeStart();
		Date swipeEnd = req.getSwipeEnd();
		Assert.notNull(swipeStart,"开始时间不能为空");
		Assert.notNull(swipeEnd, "结束时间不能为空");
		
		List<XcAttendanceStatistics> statistics = xcAttendanceService.statistics(swipeStart, swipeEnd);
		
		return RespJsonFactory.buildSuccess(statistics);
	}
	
	@RequestMapping(value="export/statistics",method=RequestMethod.GET)
	public void exportStatistics(HttpServletResponse response,XcAttendanceRecordReq req) throws ParseException{
		
		Date swipeStart = req.getSwipeStart();
		Date swipeEnd = req.getSwipeEnd();
		Assert.notNull(swipeStart,"开始时间不能为空");
		Assert.notNull(swipeEnd, "结束时间不能为空");
		
		
		List<XcAttendanceStatistics> statistics = xcAttendanceService.statistics(swipeStart, swipeEnd);
		ExcelExportUtil.export("校车考勤统计.xls", statistics, response, XcAttendanceStatistics.class);
		
		
	}
}
