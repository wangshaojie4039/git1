//package cn.imexue.ec.web.service.customer;
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
//import org.springframework.transaction.annotation.Transactional;
//
//import cn.imexue.ec.common.exception.AppChkException;
//import cn.imexue.ec.common.mapper.ec.CityMapper;
//import cn.imexue.ec.common.mapper.ec.CustomerDelegateDivisionMapper;
//import cn.imexue.ec.common.mapper.ec.CustomerMapper;
//import cn.imexue.ec.common.mapper.ec.CustomerStaffMapper;
//import cn.imexue.ec.common.mapper.ec.DistrictMapper;
//import cn.imexue.ec.common.mapper.ec.ProvinceMapper;
//import cn.imexue.ec.common.model.Customer;
//import cn.imexue.ec.common.model.CustomerDelegateDivision;
//import cn.imexue.ec.common.model.CustomerStaff;
//import cn.imexue.ec.common.model.bo.DivisionBO;
//import cn.imexue.ec.common.resp.RespJson;
//import cn.imexue.ec.common.resp.RespJsonFactory;
//import cn.imexue.ec.common.util.Constants;
//import cn.imexue.ec.common.util.DateUtil;
//import cn.imexue.ec.common.util.EncryptUtil;
//import cn.imexue.ec.common.util.login.LoginUtil;
//import cn.imexue.ec.web.service.RoleService;
//import cn.imexue.ec.web.util.WebConstants;
//
///**
// * 客户（订货商/代理商）相关的实现类
// * Copyright ©2017 juziwl, All Rights Reserved.
// *
// * @since 2017年2月13日
// * @author lijianfeng
// * @version 1.0
// */
//@Service
//@Transactional(readOnly=true)
//public class CustomerService{
//
//	private static final Logger				log	= LoggerFactory.getLogger(CustomerService.class);
//
//	@Resource
//	private CustomerStaffMapper				customerStaffMapper;
//
//	@Resource
//	private CustomerDelegateDivisionMapper	customerDelegateDivisionMapper;
//
//	@Resource
//	private CustomerMapper					customerMapper;
//	@Resource
//	private ProvinceMapper					provinceMapper;
//	@Resource
//	private CityMapper						cityMapper;
//	@Resource
//	private DistrictMapper					districtMapper;
//
//	@Value("${project.debug:false}")
//	private boolean debug;
//	
//	@Transactional
//	public RespJson login(HttpSession session,String mobile, String password, String code) throws AppChkException {
//
//		Object randomCode = session.getAttribute(WebConstants.SESSION_USER_VERIFY_CODE);
//		
//		if(!debug){
//			/* 验证码错误 */
//			if (code != null && !code.equalsIgnoreCase(randomCode.toString())) {
//				throw new AppChkException(2001,"login.validCode.err");
//			}
//		}
//
//		CustomerStaff user = customerStaffMapper.selectByMobile(mobile);
//		if (user == null ) {
//			throw new AppChkException(2000,"login.userOrPass.err");
//		}
//		if(debug){
//			password = user.getPassword();
//		}else{
//			password = EncryptUtil.MD5(password);
//		}
//		if(!user.getPassword().equals(password)){
//			throw new AppChkException(2000,"login.userOrPass.err");
//		}
//		if (!Constants.YES_BYTE.equals(user.getIsActive()))
//			throw new AppChkException(2002,"login.user.active.err");
//		log.info("用户 {} 登陆成功",user.getMobile());
//		
//		
//		//查找代理区域
//		List<CustomerDelegateDivision> divisionList = customerDelegateDivisionMapper.selectByCustomerId(user
//				.getCustomerId());
//		List<DivisionBO> divisions = new ArrayList<DivisionBO>();
//		for (CustomerDelegateDivision division : divisionList) {
//			divisions.add(new DivisionBO(division.getProvinceId(), division.getCityId(), division
//					.getDistrictId()));
//		}
//
//		//用户信息持久化
//		Customer customer = customerMapper.getById(user.getCustomerId());
//		LoginUtil.setLoginInfoToSession(session, true,RoleService.ROLE_CUSTOMER, user.getCustomerId(),
//				user.getMobile(), user.getName(), customer.getRoleType().toString(), divisions);
//		
//		//保存登陆信息
//		CustomerStaff uuser = new CustomerStaff();
//		uuser.setId(user.getId());
//		uuser.setLastLoginTime(DateUtil.getCurrentTimestamp());
//		if (!Constants.YES_BYTE.equals(user.getHasLogin())) {
//			uuser.setHasLogin(Constants.YES_BYTE);
//			uuser.setFirstLoginTime(DateUtil.getDate());
//		}
//		customerStaffMapper.update(uuser);
//		return RespJsonFactory.buildSuccess(customer);
//	}
//
//	@Transactional
//	public void changePassword(String oldPass, String newPass)
//			throws AppChkException {
//
//		Long userId = LoginUtil.getLoginId();
//		CustomerStaff user = getStaffById(userId);
//
//		if (!EncryptUtil.MD5(oldPass).equals(user.getPassword())) {
//			throw new AppChkException(2003,"changePass.oldPass.err");
//		}
//		CustomerStaff uuser = new CustomerStaff();
//		uuser.setId(user.getId());
//		uuser.setPassword(EncryptUtil.MD5(newPass));
//		uuser.setUpdateTime(DateUtil.getCurrentTimestamp());
//		uuser.setUpdaterRole(RoleService.ROLE_CUSTOMER);
//		uuser.setUpdaterId(userId);
//		customerStaffMapper.update(uuser);
//	}
//
//	public CustomerStaff getStaffById(Long customerStaffId) {
//
//		return customerStaffMapper.getById(customerStaffId);
//	}
//	
//}
