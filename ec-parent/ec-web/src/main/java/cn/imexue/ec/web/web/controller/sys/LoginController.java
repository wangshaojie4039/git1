package cn.imexue.ec.web.web.controller.sys;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.sys.LoginService;
import cn.imexue.ec.web.util.WebConstants;
import cn.imexue.ec.web.web.controller.sys.req.LoginReq;

@RestController
public class LoginController {

	@Value("${project.debug:false}")
	private boolean debug;
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	private LoginService loginService;
	
	
	/**
	 * @api {POST} /login 登陆
	 * @apiGroup login
	 * @apiName login
	 * 
	 * @apiParam username 用户名 18512525713 13600000001
	 * @apiParam password 密码
	 * @apiParam code 验证码
	 * 
	 * @apiError 2001  验证码<code>code</code>错误
	 * @apiError 2000  用户名或密码错误
	 * @apiError 2002  该用户已被禁止登录 
	 * @apiError 2003 该教师没有学校
	 * 
	 * @apiSuccess {string} role T为老师，D为园长
	 * @apiSuccessExample {json} Success-Response:
	 *  {
    	"id": 1,
    	"versionNo": 1,
    	"mobile": "13600000001",
    	"name": "王老师测试在测试",
    	"sex": 2,
    	"birthday": null,
    	"hasLogin": null,
    	"isActive": 1,
    	"role": "T",
    	"schoolId": 1,
    	"logoUrl": null
  		}
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public RespJson login(HttpServletRequest request,
			@RequestBody(required=true) LoginReq loginreq){
		String username = loginreq.getUsername();
		String password = loginreq.getPassword();
		String code = loginreq.getCode();
		Assert.notNull(username,"用户名不能为空");
		if(!debug){
			Assert.notNull(password,"密码不能为空");
			Assert.notNull(username,"用户名不能为空");
			HttpSession session = request.getSession();
			
			Object randomCode = session.getAttribute(WebConstants.SESSION_USER_VERIFY_CODE);
			if(randomCode==null||!((String)randomCode).equalsIgnoreCase(code)){
				throw new AppChkException(2001,"login.validCode.err");
			}
		}
		TeacherVo login = loginService.login(request, username, password);
		
		return RespJsonFactory.buildSuccess(login);
	}
	
	/**
	 * @api {GET} /logout 登出
	 * @apiGroup logout
	 * @apiName logout
	 */
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public RespJson logout(HttpSession session){
		if(LoginUtil.getLoginInfo()!=null){
			log.debug("用户id{}退出",LoginUtil.getLoginId());
		}
		session.invalidate();
		return RespJsonFactory.buildSuccess();
	}
}
