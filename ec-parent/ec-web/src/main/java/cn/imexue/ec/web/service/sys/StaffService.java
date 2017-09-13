//package cn.imexue.ec.web.service.sys;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import cn.imexue.ec.common.exception.AppChkException;
//import cn.imexue.ec.common.mapper.ec.StaffProvinceMapper;
//import cn.imexue.ec.common.mapper.ec.SysStaffMapper;
//import cn.imexue.ec.common.model.StaffProvince;
//import cn.imexue.ec.common.model.SysStaff;
//import cn.imexue.ec.common.model.bo.DivisionBO;
//import cn.imexue.ec.common.resp.RespJson;
//import cn.imexue.ec.common.resp.RespJsonFactory;
//import cn.imexue.ec.common.service.sys.SMSTencentService;
//import cn.imexue.ec.common.util.Constants;
//import cn.imexue.ec.common.util.DateUtil;
//import cn.imexue.ec.common.util.EncryptUtil;
//import cn.imexue.ec.common.util.login.LoginUtil;
//import cn.imexue.ec.web.service.RoleService;
//import cn.imexue.ec.web.util.WebConstants;
//
///**
// * 员工服务接口 Copyright ©2017 juziwl, All Rights Reserved.
// *
// * @since 2017年2月23日
// * @author lijianfeng
// * @version 1.0
// */
//@Service
//public class StaffService{
//
//	@SuppressWarnings("unused")
//	private static final Logger log = LoggerFactory.getLogger(StaffService.class);
//
//	@Resource
//	private SysStaffMapper sysStaffMapper;
//
//	@Resource
//	private StaffProvinceMapper staffProvinceMapper;
//
//	@Resource
//	private SMSTencentService sMSTencentService;
//
//	@Value("${project.debug:false}")
//	private boolean debug;
//
//	public RespJson login(HttpSession session, String loginId, String password,
//			String code) throws AppChkException {
//
//		Object randomCode = session.getAttribute(WebConstants.SESSION_USER_VERIFY_CODE);
//
//		/* 验证码错误 */
//		if (!debug&& (code != null && !code.equalsIgnoreCase(randomCode.toString()))) {
//			throw new AppChkException(2001,"login.validCode.err");
//		}
//
//		SysStaff user = sysStaffMapper.selectByLoginId(loginId);
//		if (user == null|| (!debug&&!EncryptUtil.MD5(password).equals(user.getPassword()))) 
//			throw new AppChkException(2000,"login.userOrPass.err");
//
//		if (!Constants.YES_BYTE.equals(user.getAllowLogin())) {
//			throw new AppChkException(2002,"login.user.active.err");
//		}
//		
//
//		List<DivisionBO> divisions = new ArrayList<DivisionBO>();
//		if (RoleService.STAFF_ROLE_MARKET_MANAGER.equals(user.getStaffRole())
//				|| RoleService.STAFF_ROLE_MARKET_STAFF.equals(user.getStaffRole())) {
//			List<StaffProvince> divisionList = staffProvinceMapper.selectByStaffId(user.getId());
//			for (StaffProvince division : divisionList) {
//				divisions.add(new DivisionBO(division.getProvinceId(), 0L,0L));
//			}
//		}
//		LoginUtil.setLoginInfoToSession(session, false, RoleService.ROLE_STAFF, user.getId(),
//				user.getLoginId(), user.getName(), user.getStaffRole(), divisions);
//
//		user.setLastLoginTime(DateUtil.getCurrentTimestamp());
//		if (!Constants.YES_BYTE.equals(user.getHasLogin())) {
//			user.setHasLogin(Constants.YES_BYTE);
//			user.setFirstLoginTime(DateUtil.getDate());
//		}
//		sysStaffMapper.update(user);
//		return RespJsonFactory.buildSuccess(user);
//	}
//}
