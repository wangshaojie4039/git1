package cn.imexue.ec.web.service.xc;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.mapper.ec.CardMapper;
import cn.imexue.ec.common.mapper.ec.XcDriverMapper;
import cn.imexue.ec.common.model.XcDriver;
import cn.imexue.ec.common.model.vo.CardVo;
import cn.imexue.ec.common.util.Constants;
import cn.imexue.ec.common.util.StringUtil;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.device.CardService;

/**
 * 
 * 文件名称： cn.imexue.ec.web.service.xc.XcDriverService.java</br>
 * 初始作者： wangshaojie</br>
 * 创建日期： 2017年7月10日</br>
 * 功能说明： 校车司机逻辑实现类 <br/> 
 * 
 * =================================================<br/> 
 * 修改记录：<br/> 
 * 修改作者        日期       修改内容<br/> 
 * 
 * 
 * ================================================<br/> 
 *  Copyright (橘子股份-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Service
@Transactional(readOnly = true)
public class XcDriverService  {

	@Resource
	private XcDriverMapper xcDriverMapper;
	
	@Resource
	private CardMapper cardMapper;
	
	@Resource
	private CardService cardService;
	
	/**
	 * 
	 *
	 * 方法描述: [分页获取当前页数据]</br>
	 * [schoolId 学校id]<br/> 
	 * [name 司机姓名]<br/> 
	 * [mobile 司机电话]<br/> 
	 * [pageParam....分页参数]<br/> 
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月10日-下午2:37:24<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param map
	 * @return
	 * List<XcDriver>
	 *
	 */
	public List<XcDriver> pageLists(Map<String, Object> map) {

		List<XcDriver> list =xcDriverMapper.pageList(map) ;
		return list;
	}

	/**
	 * 
	 *
	 * 方法描述: [根据id获取]</br>
	 * [id]<br/> 
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月10日-下午2:54:54<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param id
	 * @return
	 * XcDriver
	 *
	 */
	public XcDriver getById(Long id){
		
		return xcDriverMapper.getById(id);
	}
	@Transactional
	public void deleteById(Long id ,Integer  versionNo){
		
		XcDriver driver=xcDriverMapper.getById(id);
		if(driver==null){
			throw new DataNoFountException("driver");
		}
		
		driver.setIsDelete(Constants.YES_BYTE);
		driver.setVersionNo(versionNo);
		xcDriverMapper.update(driver);
		cardMapper.updateUserToNull(driver.getAttendanceCardNo());
	}

	/**
	 * 
	 *
	 * 方法描述: [保存司机信息]</br>
	 * [driver 司机信息]<br/> 
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月10日-下午3:47:29<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param driver
	 * void
	 *
	 */
	@Transactional
	public void save(XcDriver driver){
		Long schoolId=LoginUtil.getSchoolId();
		Long driverId=driver.getId();
		XcDriver oldDriver=null;
		if(driverId==null){
			driver.setSchoolId(schoolId);
			driver.setIsDelete(Constants.NO_BYTE);
			xcDriverMapper.insert(driver);
			driverId=driver.getId();
		}else{
			oldDriver=xcDriverMapper.getById(driverId);
			//如果变更考勤卡号，则将原来的卡解绑
			if(StringUtil.isNotEmpty(oldDriver.getAttendanceCardNo())&&!oldDriver.getAttendanceCardNo().equals(driver.getAttendanceCardNo())){
				cardMapper.updateUserToNull(oldDriver.getAttendanceCardNo());
			}
			xcDriverMapper.update(driver);
		}
		CardVo card=new CardVo();
		card.setCardNo(driver.getAttendanceCardNo());
		card.setUserId(driverId);
		card.setSchoolId(schoolId);
		card.setUserType(Constants.CARD_USER_TYPE_DRIVER);
		CardVo oldCard=cardMapper.getByCardNo(driver.getAttendanceCardNo());
		if(oldCard!=null){
			card.setVersionNo(oldCard.getVersionNo());
		}
		cardService.save(card);
	}
}
