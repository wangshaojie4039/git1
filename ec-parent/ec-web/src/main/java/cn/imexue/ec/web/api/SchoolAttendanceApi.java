package cn.imexue.ec.web.api;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.imexue.ec.web.api.model.AttendanceSchoolModel;

@FeignClient(name="api",url="${ec.api.url}")
@RequestMapping("attendance")
public interface SchoolAttendanceApi {

	@RequestMapping(value="school",method=RequestMethod.POST)
	public Map<String, Object> school(@RequestHeader("AccessToken") String AccessToken,
			@RequestBody AttendanceSchoolModel api);
	
	@RequestMapping(value="class",method=RequestMethod.POST)
	public Map<String, Object> clazz(@RequestHeader("AccessToken") String AccessToken,
			@RequestBody AttendanceSchoolModel api);
	
	@RequestMapping(value="class/detail",method=RequestMethod.POST)
	public Map<String, Object> classDetail(@RequestHeader("AccessToken") String AccessToken,
			@RequestBody AttendanceSchoolModel api);
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	public Map<String, Object> update(@RequestHeader("AccessToken") String AccessToken,
			@RequestBody AttendanceSchoolModel api);
	
}
