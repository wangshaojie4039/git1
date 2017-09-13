package cn.imexue.ec.web.service.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.mapper.ec.LoginMapper;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.util.Md5Util;
import cn.imexue.ec.common.util.login.LoginUtil;

@Service
@Transactional(readOnly=true)
public class LoginService {

	@Resource
	private LoginMapper loginMapper;
	
	@Value("${project.debug:false}")
	private boolean debug;
	
	public TeacherVo login(HttpServletRequest request,String username,String password){
		TeacherVo teacher = loginMapper.login(username);
		if(teacher==null){
			throw new AppChkException(2000,"login.userOrPass.err");
		}
		password = Md5Util.encrypt(password);
		if(!debug&&!password.equals(teacher.getPassword())){
			throw new AppChkException(2000,"login.userOrPass.err");
		}
		if(!teacher.getIsActive().equals(new Byte("1"))){
			throw new AppChkException(2002,"login.user.active.err");
		}
		if(teacher.getSchoolId()==null){
			throw new AppChkException(2003,"login.user.no.school");
		}
		HttpSession session = request.getSession();
		
		LoginUtil.setLoginInfoToSession(session, teacher.getRole(), teacher.getId(), username, 
				teacher.getName(), "T", teacher.getSchoolId());
		
		return teacher;
	}
	
	
	
	
}
