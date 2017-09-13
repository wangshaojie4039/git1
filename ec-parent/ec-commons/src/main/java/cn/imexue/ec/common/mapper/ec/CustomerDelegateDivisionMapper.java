package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.CustomerDelegateDivision;
import cn.imexue.ec.common.model.bo.PcdBO;

public interface CustomerDelegateDivisionMapper{

	CustomerDelegateDivision selectCustomerDelegateInfo(Long provinceId, Long cityId, Long districtId);

	/**
	 * 获取代理商所代理的区域
	 */
	List<CustomerDelegateDivision> selectByCustomerId(Long customerId);

	/**
	 * 获得代理商代理的省市区三级Ids列表
	 *
	 * @param customer
	 * @return
	 */
	List<PcdBO> selectPcdIdsByCustomerId(Long customerId);

	/**
	 * 获取代理商的顶级代理区域
	 *
	 * @return 群的相关信息
	 * @throws Exception
	 *             抛出异常
	 */
	List<CustomerDelegateDivision> selectTopByCustomerId(Long customerId);

	/**
	 * <p>
	 * 根据customerId获取代理商下的学校数目
	 * </p>
	 */
	Integer selectSchoolNUmByCustomerId(Long customerId);

	/**
	 * 方法描述: [搜索区域内的代理商.]<br/>
	 * 初始作者: 崔业新<br/>
	 * 创建日期: 2017年4月7日-上午11:20:26<br/>
	 * 开始版本: 1.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param provinceId
	 * @param cityId
	 * @param districtId
	 * @return
	 *         List<CustomerDelegateDivision>
	 */
	List<CustomerDelegateDivision> selectCustomerInfo(@Param(value = "provinceId") Long provinceId, @Param(value = "cityId") Long cityId,
			@Param(value = "districtId") Long districtId);

	/**
	 * 方法描述: [搜索区域内的代理商包括未激活]<br/>
	 * 初始作者: 崔业新<br/>
	 * 创建日期: 2017年4月22日-下午3:22:21<br/>
	 * 开始版本: 1.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 * 
	 * @param provinceId
	 * @param cityId
	 * @param districtId
	 * @return
	 *         List<CustomerDelegateDivision>
	 */
	List<CustomerDelegateDivision> selectCustomerInfomation(@Param(value = "provinceId") Long provinceId, @Param(value = "cityId") Long cityId,
			@Param(value = "districtId") Long districtId);

	/**
	 * 方法描述: [根据customerId删除所有代理区域.]<br/>
	 * 初始作者: 崔业新<br/>
	 * 创建日期: 2017年3月23日-下午8:52:17<br/>
	 * 开始版本: 1.0.0<br/>
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者 日期 修改内容<br/>
	 * ================================================<br/>
	 *
	 * @param customerId
	 * @return
	 *         Integer
	 */
	Integer deleteByCustomerId(Long customerId);

}
