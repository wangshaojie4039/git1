package cn.imexue.ec.common.mapper.ec;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.CustomerStaff;

public interface CustomerStaffMapper extends BaseMapper<CustomerStaff>{

	CustomerStaff selectByMobile(String mobile);

	/**
	 * 方法描述: [根据customerId搜索.]<br/>
	 * 初始作者: 崔业新<br/>
	 * 创建日期: 2017年3月23日-下午8:47:19<br/>
	 * 开始版本: 1.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param customerId
	 * @return
	 *         CustomerStaff
	 */
	CustomerStaff selectByCustomerId(Long customerId);

}
