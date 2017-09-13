package cn.imexue.ec.web;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.imexue.ec.web.api.SchoolAttendanceApi;
import cn.imexue.ec.web.api.model.AttendanceSchoolModel;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppTest2 {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private SchoolAttendanceApi schoolAttendanceApi;
	
	@Test
	public void test(){
		AttendanceSchoolModel api = new AttendanceSchoolModel();
		api.setDate("2017-07-03");
		api.setSchoolId(1048l);
		api.setAttendDiv("1");
		api.setInOutDiv("in");
		api.setClassId(100045l);
		String token = "T18512525713|e10adc3949ba59abbe56e057f20f883e";
		
		Map<String, Object> map2 = schoolAttendanceApi.classDetail(token, api);
		log.debug("{}",map2);
	
	}
	
	
}
