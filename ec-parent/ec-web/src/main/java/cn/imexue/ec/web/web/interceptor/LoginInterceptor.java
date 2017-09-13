package cn.imexue.ec.web.web.interceptor;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Stream;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.cache.VerifyCacheService;

/**
 * 登陆类拦截器
 * 
 * @author hl
 */
public class LoginInterceptor extends HandlerInterceptorAdapter implements Serializable {

	/**
	 *
	 */
	private static final long	serialVersionUID	= -1286550732069312355L;

	private Logger				log					= LoggerFactory.getLogger(getClass());

	private static final String	VERIFY_KEY			= "verifyKeys";

	private static final String	LAST_VISIT			= "lastVisit";

	@Value("${verify.timeout.second}")
	private Integer				verifyTimeoutSecond;

	@Value("${project.debug}")
	private Boolean				debug;

	@Resource
	private VerifyCacheService	verifyCacheService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		LoginUtil.setLoginFromRequest(request);
		HttpSession session = request.getSession();
		try {
			if (!session.isNew()) {
				verify(session, request);
			}
			return true;
		} catch (Exception e) {
			log.debug("未通过验证,ip:{}", request.getRemoteAddr());
			return debug;
		} finally {
			Cookie cookie = setVerifyKey(session, request);
			response.addCookie(cookie);
			log.debug("cookie设置key:{}", cookie.getValue());
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
					throws Exception {

		LoginUtil.removeLoginInfo();

	}

	private void verify(HttpSession session, HttpServletRequest request) {

		Date lastVisit = (Date) session.getAttribute(LAST_VISIT);
		if (lastVisit == null) {
			return;
		}
		// 1分钟以后不验证
		Calendar instance = Calendar.getInstance();
		instance.setTime(lastVisit);
		instance.add(Calendar.SECOND, verifyTimeoutSecond);
		if (new Date().after(instance.getTime())) {
			return;
		}
		Cookie[] cookies = request.getCookies();
		String value = null;
		for (Cookie cookie : cookies) {
			String name = cookie.getName();
			if (name.equals(VERIFY_KEY)) {
				value = cookie.getValue();
				break;
			}
		}
		if (StringUtil.isEmpty(value)) {
			log.debug("value2为空");
			throw new AppChkException(1012, "system.login.varify");
		}

		String value2 = verifyCacheService.getValue(value);
		if (!"1".equals(value2)) {
			log.debug("未找到token:{}", value);
		}

	}

	private Cookie setVerifyKey(HttpSession session, HttpServletRequest request) {

		session.setAttribute(LAST_VISIT, new Date());
		String random = getRandom();
		verifyCacheService.setValue(random);
		Cookie cookie = null;
		if (request.getCookies() == null) {
			cookie = new Cookie(VERIFY_KEY, random);
		} else {
			Optional<Cookie> findFirst = Stream.of(request.getCookies()).filter(x -> x.getName().equals(VERIFY_KEY)).findFirst();
			if (findFirst.isPresent()) {
				cookie = findFirst.get();
				cookie.setValue(random);
			} else {
				cookie = new Cookie(VERIFY_KEY, random);
			}
		}

		cookie.setHttpOnly(true);
		cookie.setMaxAge(-1);
		cookie.setDomain("");
		cookie.setPath("/");

		return cookie;
	}

	private String getRandom() {

		return "verifyValue=" + StringUtil.getRandomString(6);
		// double nextDouble = RandomUtils.nextDouble(0d, 10000d);
		// return ""+nextDouble;
	}

}
