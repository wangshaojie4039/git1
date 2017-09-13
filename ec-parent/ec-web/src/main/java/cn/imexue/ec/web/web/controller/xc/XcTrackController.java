package cn.imexue.ec.web.web.controller.xc;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.vo.XcLineItemVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.xc.xcLine.XcLineManagerService;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@Role(RoleType.D)
@RestController
@RequestMapping("xc/track")
public class XcTrackController {

	
	@Resource
	private XcLineManagerService xcLineManagerService;
	
	@RequestMapping(value="info/{lineId}",method=RequestMethod.GET)
	public RespJson info(@PathVariable("lineId")Long lineId){
		
		Long schoolId = LoginUtil.getSchoolId();
		XcLineItemVo xcLine = xcLineManagerService.getTrack(schoolId, lineId);
		
		return RespJsonFactory.buildSuccess(xcLine);
	}
	
}
