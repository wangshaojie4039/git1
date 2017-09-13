package cn.imexue.ec.web.service.attendance.school;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.dao.mongo.MongoBaseDao;
import cn.imexue.ec.common.mapper.ec.ChildMapper;
import cn.imexue.ec.common.mapper.ec.ClassMapper;
import cn.imexue.ec.common.mapper.ec.TeacherMapper;
import cn.imexue.ec.common.model.vo.ChildAttendanceDto;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.util.DateUtil;
import cn.imexue.ec.web.api.SchoolAttendanceApi;
import cn.imexue.ec.web.api.model.AttendanceSchoolModel;
import cn.imexue.ec.web.model.mongo.ChildAttendanceHistory;

@Service
@Transactional(readOnly=true)
public class SchoolAttendanceService {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private MongoBaseDao mongoBaseDao;
	
	@Resource
	private SchoolAttendanceApi schoolAttendanceApi;
	
	@Resource
	private TeacherMapper teacherMapper;
	
	@Resource
	private ClassMapper classMapper;
	
	@Resource
	private ChildMapper childMapper;
	
	@Resource
	private SchoolAttendanceCardService schoolAttendanceCardService;
	
	private ExecutorService executor = Executors.newCachedThreadPool();
	
	/**
	 * 按班级查询历史考勤,如果classId为null则查全校
	 * @param schoolId
	 * @param classId
	 * @param from
	 * @param to
	 * @return
	 */
	public List<ChildAttendanceHistory> getHistoryClassAttendance(Long schoolId,Long classId,Date from,Date to){
		
		//处理时间
		Calendar instance = Calendar.getInstance();
		instance.setTime(from);
		instance.set(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DATE),
				0, 0, 0);
		from = instance.getTime();
		instance.setTime(to);
		instance.set(instance.get(Calendar.YEAR), instance.get(Calendar.MONTH), instance.get(Calendar.DATE),
				23, 59, 59);
		to = instance.getTime();
		
		MongoTemplate mongoTemplate = mongoBaseDao.getMongoTemplate();
		
		Query query = Query.query(Criteria.where("schoolId").is(schoolId).andOperator(Criteria.where("classId").is(classId).andOperator(
				Criteria.where("date").gte(from)
				,Criteria.where("date").lte(to))));
		
		List<ChildAttendanceHistory> list = mongoTemplate.find(query, ChildAttendanceHistory.class);
		list.forEach(a->{
			//按班级排序
			a.getAbsenteeisms().sort((x,y)->x.getClassId().compareTo(y.getClassId()));
			a.getNormals().sort((x,y)->x.getClassId().compareTo(y.getClassId()));
		});
		//按日期排序
		list.sort((x,y)->x.getDate().compareTo(y.getDate()));
		return list;
	}
	
	public Map<String, Object> school(Long schoolId,Long loginId){
		
		TeacherVo teacher = teacherMapper.getTeacherById(loginId, schoolId);
		
		AttendanceSchoolModel model = new AttendanceSchoolModel();
		model.setSchoolId(schoolId);
		model.setDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		return schoolAttendanceApi.school(teacher.getUid()+"|"+teacher.getPassword(), model);
	}
	
	public Map<String, Object> clazz(Long schoolId,Long loginId,Long classId){
		
		TeacherVo teacher = teacherMapper.getTeacherById(loginId, schoolId);
		
		AttendanceSchoolModel model = new AttendanceSchoolModel();
		model.setClassId(classId);
		model.setSchoolId(schoolId);
		model.setDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		return schoolAttendanceApi.clazz(teacher.getUid()+"|"+teacher.getPassword(), model);
	}
	
	public List<ChildAttendanceDto> classDetail(Long loginId,Long classId,Long schoolId,String attendDiv,boolean inDiv){
		String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
		Boolean normal = null;
		if("1".equals(attendDiv)){
			normal = true;
		}else{
			normal = false;
		}
		final boolean norm = normal;
		List<ChildAttendanceDto> schoolAttendance = schoolAttendanceCardService.getSchoolAttendance(schoolId, date, classId,normal);
		
		Map<String, Object> param = new HashMap<>();
		param.put("schoolId", schoolId);
		param.put("classId", classId);
		List<ChildVo> pageList = childMapper.pageList(param);
		
		List<ChildAttendanceDto> result = new ArrayList<>();
		
		pageList.forEach(x->{
			ChildAttendanceDto dto = new ChildAttendanceDto();
			dto.setChildName(x.getName());
			dto.setClassId(classId);
			dto.setLogo(x.getLogoUrl());
			dto.setChildId(x.getId());
			
			Optional<ChildAttendanceDto> optionalIn = schoolAttendance.stream().filter(y->{
				return y.getChildId().equals(x.getId());
				}).findFirst();
			
			if(optionalIn.isPresent()){
				ChildAttendanceDto childAttendanceDto = optionalIn.get();
				dto.setInReason(childAttendanceDto.getInReason());
				dto.setInStatus(childAttendanceDto.getInStatus());
				dto.setInReasonType(childAttendanceDto.getInReasonType());
				dto.setOutReason(childAttendanceDto.getOutReason());
				dto.setOutStatus(childAttendanceDto.getOutStatus());
				dto.setOutReasonType(childAttendanceDto.getOutReasonType());
			}
			if(inDiv){
				if(norm){
					if(new Integer(2).equals(dto.getInStatus())){
						log.debug("xxxxxxxxxxxxx:{}",dto);
					}
					//签到正常考勤
					if(new Integer(1).equals(dto.getInStatus())||new Integer(2).equals(dto.getInStatus())){
						result.add(dto);
					}
				}else{
					//签到异常考勤
					if(!(new Integer(1).equals(dto.getInStatus())||new Integer(2).equals(dto.getInStatus()))){
						result.add(dto);
					}
				}
			}else{
				if(norm){
					//签退正常考勤
					if(new Integer(1).equals(dto.getOutStatus())||new Integer(2).equals(dto.getOutStatus())){
						result.add(dto);
					}
				}else{
					//签退异常考勤
					if(!(new Integer(1).equals(dto.getOutStatus())||new Integer(2).equals(dto.getOutStatus()))){
						result.add(dto);
					}
				}
			}
			
		});
		
		
		
		return result;
	}
	
	public Map<String, Object> update(Long loginId,AttendanceSchoolModel model){
		
		Long childId = model.getChildId();
		ChildVo childVo = childMapper.getChild(childId, model.getSchoolId());
		Long classId = childVo.getClassId();
		model.setClassId(classId);
		TeacherVo teacher = teacherMapper.getTeacherById(loginId, model.getSchoolId());
		
		model.setDate(DateUtil.formatDate(new Date(), "yyyy-MM-dd"));
		
		Map<String, Object> map = schoolAttendanceApi.update(teacher.getUid()+"|"+teacher.getPassword(), model);
		log.debug("考勤设置:{}",model);
		return map;
	}
	
	public List<ChildAttendanceDto> schoolDetail(Long loginId,Long schoolId,String attendDiv,boolean inDiv){
		
		List<ClassVo> classes = classMapper.getClassBySchoolId(schoolId);
		
		List<Callable<List<ChildAttendanceDto>>> list= new ArrayList<>();
		classes.forEach(x-> {
			list.add(()->{
				return classDetail(loginId,x.getId() , schoolId, attendDiv,inDiv);
			});
		});
		List<ChildAttendanceDto> l = new ArrayList<>();
		try {
			List<Future<List<ChildAttendanceDto>>> invokeAll = executor.invokeAll(list);
				invokeAll.stream().forEach(x->{
					try {
					l.addAll(x.get());
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		l.sort((x,y)->(x).getClassId().compareTo(y.getClassId()));
		return l;
	}
	
}
