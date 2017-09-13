package cn.imexue.ec.common.util.login;

import java.util.List;

import cn.imexue.ec.common.model.bo.DivisionBO;

/**
 * 登陆信息接口
 * @author hl
 *
 */
public interface LoginInterface {

	String getUserRole();
	
	Long getUserId();
	
	String getLoginId();
	
	String getName();
	
	String getRole();
	
	List<DivisionBO> getDivisions();
	
}
