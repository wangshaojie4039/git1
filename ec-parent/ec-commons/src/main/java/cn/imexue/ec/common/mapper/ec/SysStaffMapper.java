package cn.imexue.ec.common.mapper.ec;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.SysStaff;

public interface SysStaffMapper extends BaseMapper<SysStaff>{

	/**
	 * 根据登录账户获取员工信息
	 *
	 * @param loginId
	 *            登录账户
	 * @return 员工实体类
	 */
	SysStaff selectByLoginId(String loginId);

	
}
