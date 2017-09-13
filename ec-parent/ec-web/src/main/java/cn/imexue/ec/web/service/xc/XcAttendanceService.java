package cn.imexue.ec.web.service.xc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.mongo.xc.XcAttendanceMongoDao;
import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.XcLineMapper;
import cn.imexue.ec.common.model.XcLine;
import cn.imexue.ec.common.model.mongo.XcChildAttendance;
import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.mongo.XcAttendanceStatistics;
import cn.imexue.ec.web.model.mongo.xc.XcAttendanceDetail;
import cn.imexue.ec.web.model.mongo.xc.XcClassDetail;
import cn.imexue.ec.web.model.mongo.xc.XcLineStatistics;

@Service
@Transactional(readOnly = true)
public class XcAttendanceService {

	@Resource
	private XcAttendanceMongoDao attendanceMongoDao;

	@Resource
	private XcLineMapper xcLineMapper;
	
	@Resource
	private ClassMapper classMapper;
	
	public List<XcChildAttendance> attendanceRecord(String childName,
			String plateNumber, Long classId, Date swipeStart, Date swipeEnd,
			Long lineId, Integer attType) {

		Criteria criteria = Criteria.where("swipeTime").exists(true);

		Long schoolId = LoginUtil.getSchoolId();
		criteria.and("schoolId").is(schoolId);
		if (childName != null) {
			criteria.and("userName").regex(".?(" + childName + "){1}.?");
		}
		if (plateNumber != null) {
			criteria.and("plateNumber").regex(".?(" + plateNumber + "){1}.?");
		}
		if (classId != null) {
			criteria.and("classId").is(classId);
		}
		if (lineId != null) {
			criteria.and("lineId").is(lineId);
		}
		if (attType != null) {
			criteria.and("attType").is(attType);
		}
		Criteria[] criteria2 = new Criteria[0];
		if (swipeStart != null) {
			criteria2 = ArrayUtils.add(criteria2, Criteria.where("swipeTime")
					.gte(swipeStart));
		}
		if (swipeEnd != null) {
			Calendar instance = Calendar.getInstance();
			instance.setTime(swipeEnd);
			instance.set(Calendar.DAY_OF_MONTH, instance.get(Calendar.DAY_OF_MONTH)+1);
			swipeEnd = instance.getTime();
			criteria2 = ArrayUtils.add(criteria2, Criteria.where("swipeTime")
					.lte(swipeEnd));
		}
		if (criteria2.length > 0) {
			criteria.andOperator(criteria2);
		}
		Query query = Query.query(criteria);

		return attendanceMongoDao.query(query);
	}

	public List<XcAttendanceStatistics> statistics(Date start, Date end)
			throws ParseException {
		List<XcAttendanceStatistics> result = new ArrayList<>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		
		Calendar instance = Calendar.getInstance();
		instance.setTime(end);
		instance.set(Calendar.DAY_OF_MONTH, instance.get(Calendar.DAY_OF_MONTH)+1);
		end = instance.getTime();
		long between = DateUtil.getBetween(start, end);
		if(between>7){
			throw new AppChkException(3103,"system.time.between.too.large");
		}
		
		String startStr = format.format(start);
		String endStr = format.format(end);
		
		
		
		for (Date date = format.parse(startStr); date.compareTo(format
				.parse(endStr)) < 0; date = DateUtil.addDays(date, 1)) {
			//每天生成一个

			XcAttendanceStatistics attendanceStatistics = getByDate(date);
			result.add(attendanceStatistics);

		}

		return result;
	}

	private XcAttendanceStatistics getByDate(Date attDay) {
		XcAttendanceStatistics statics = new XcAttendanceStatistics();
		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
		String attDate = format2.format(attDay);
		statics.setAttDate(attDay);

		Long schoolId = LoginUtil.getSchoolId();
		Criteria criteria = Criteria.where("attDay").is(attDate);
		criteria.and("schoolId").is(schoolId);
		Query query = Query.query(criteria);
		List<XcChildAttendance> list = attendanceMongoDao.query(query);

		//上学应乘
		List<XcChildAttendance> upTotal = list.stream().filter(
				x -> x.getAttType() == 1).collect(Collectors.toList());
		statics.setUpTotal(upTotal.size());
		//上学实乘
		List<XcChildAttendance> up = upTotal.stream()
				.filter(x -> x.getSwipeTime() != null||"TC".equals(x.getTeacherCheck())).collect(Collectors.toList());
		statics.setUp(up.size());

		//放学应乘
		List<XcChildAttendance> downTotal = list.stream().filter(x->x.getAttType()==3).collect(Collectors.toList());
		statics.setDownTotal(downTotal.size());
		//放学实乘
		List<XcChildAttendance> down = downTotal.stream().filter(x->x.getSwipeTime()!=null||"TC".equals(x.getTeacherCheck())).collect(Collectors.toList());
		statics.setDown(down.size());
		
		
		
		
		//按线路区分
		Map<Long, List<XcChildAttendance>> lineMapTotal = list.stream().collect(Collectors.groupingBy(XcChildAttendance::getLineId));
		List<XcLineStatistics> xcLines = new ArrayList<>();
		for(Map.Entry<Long, List<XcChildAttendance>> entry : lineMapTotal.entrySet()){
			XcLineStatistics stati = new XcLineStatistics();
			stati.setLineId(entry.getKey());
			XcLine xcLine = xcLineMapper.getById(entry.getKey());
			if(xcLine==null){
				//如果线路不存在，则取第一条
				stati.setLineName(entry.getValue().get(0).getLineName());
			}else{
				stati.setLineName(xcLine.getLineName());
			}
			
			List<XcChildAttendance> list2 = entry.getValue();
			//上学 
			stati.setUpTotal((int)list2.stream().filter(x->x.getAttType()==1).count());
			long count = list2.stream().filter(x->x.getAttType()==1&&(x.getSwipeTime()!=null||"TC".equals(x.getTeacherCheck()))).count();
			stati.setUp((int) count);
			//放学
			stati.setDownTotal((int)list2.stream().filter(x->x.getAttType()==3).count());
			long count2 = list2.stream().filter(x->x.getAttType()==3&&(x.getSwipeTime()!=null||"TC".equals(x.getTeacherCheck()))).count();
			stati.setDown((int) count2);
			
			xcLines.add(stati);
		}
		
		statics.setLines(xcLines);
		statics.setUpList(getXcAttendanceDetail(upTotal));
		statics.setDownList(getXcAttendanceDetail(downTotal));
		return statics;
	}
	
	//按班级分
	private XcAttendanceDetail getXcAttendanceDetail(List<XcChildAttendance> list){

		XcAttendanceDetail detail = new XcAttendanceDetail();
		List<XcClassDetail> ride = new ArrayList<>();
		List<XcClassDetail> noRide = new ArrayList<>();
		detail.setRide(ride);
		detail.setNoRide(noRide);
		Map<Long, List<XcChildAttendance>> collect = list.stream().collect(Collectors.groupingBy(XcChildAttendance::getClassId));
		for(Map.Entry<Long, List<XcChildAttendance>> entry : collect.entrySet()){
			XcClassDetail classDetail = new XcClassDetail();
			XcClassDetail classDetail2 = new XcClassDetail();
			Long classId = entry.getKey();
			classDetail.setClassId(classId);
			classDetail2.setClassId(classId);
			//BUG390 
//			ClassVo classVo = classMapper.getById(classId);
//			if(classVo == null){
				classDetail.setClassName(entry.getValue().get(0).getClassName());
				classDetail2.setClassName(entry.getValue().get(0).getClassName());
//			}else{
//				classDetail.setClassName(classVo.getName());
//				classDetail2.setClassName(classVo.getName());
//			}
			//乘坐了的孩子
			List<String> rideName = entry.getValue().stream().filter(x->x.getSwipeTime()!=null||"TC".equals(x.getTeacherCheck())).map(XcChildAttendance::getUserName).collect(Collectors.toList());
			classDetail.setChildren(rideName);
			List<String> noRideName = entry.getValue().stream().filter(x->x.getSwipeTime()==null&&!"TC".equals(x.getTeacherCheck())).map(XcChildAttendance::getUserName).collect(Collectors.toList());
			classDetail2.setChildren(noRideName);
			//已乘
			if(rideName.size()>0){
				ride.add(classDetail);
			}
			//未乘
			if(noRideName.size()>0){
				noRide.add(classDetail2);
			}
		}
		return detail;
	}
	
	
	public static void main(String[] args) {
		Stream<Integer> of = Stream.of(1,2,3);
		System.out.println(of.count());
		System.out.println(of.filter(x->x<2).count());
		System.out.println(of.filter(x->x<3).count());
	}
}
