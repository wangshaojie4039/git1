package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.XcDriver;

@Mapper
public interface XcDriverMapper extends BaseMapper<XcDriver> {


	List<XcDriver> pageList(Map<String, Object> map);
	
	/**
	 * 
	 * 方法描述: [根据驾驶证编号查询]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月26日-下午5:35:05<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param licenseNo
	 * @return
	 * XcDriver
	 *
	 */
	XcDriver selectByLicenseNo(String licenseNo);
	
	/**
	 * 
	 *
	 * 方法描述: [根据身份证号查询]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月26日-下午5:40:22<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param idNo
	 * @return
	 * XcDriver
	 *
	 */
	XcDriver selectByIdNo(String idNo);
	
	
}
