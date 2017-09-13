package cn.imexue.ec.web.web.controller.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.imexue.ec.common.model.SysCode;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.service.sys.SMSTencentService;
import cn.imexue.ec.common.service.sys.SysCodeService;
import cn.imexue.ec.web.web.role.Role;

/**
 * 文件名称： cn.imexue.ec.web.web.controller.sys.SysCodeController.java</br>
 * 初始作者： zhongxp</br>
 * 创建日期： 2017年4月7日</br>
 * 功能说明： 系统code <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@RestController
@RequestMapping("/code")
public class SysCodeController {

	@Resource
	private SysCodeService	sysCodeService;

	@Resource
	private SMSTencentService sMSTencentService;
	
	/**
	 * @api {GET} /code/staffRoleList 暂时无用
	 * @apiGroup system
	 * @apiName  staffRoleList
	 */
	@Role
	@RequestMapping(value="/staffRoleList",method=RequestMethod.GET)
	public RespJson getByType() {
		sysCodeService.init();
		List<SysCode> list = SysCodeService.getStaffRoleList();
		return RespJsonFactory.buildSuccess(list);
	}

	@RequestMapping(value="smsKey",method=RequestMethod.GET)
	public RespJson smsKey(String key){
		if(key != null){
			sMSTencentService.setSendSms("1".equals(key));
		}
		return RespJsonFactory.buildSuccess(sMSTencentService.isSendSms());
	}
	/**
	 * @api {GET} /no 接口返回值说明
	 * @apiGroup ErrorCode
	 * @apiName  ErrorCode
	 * @apiDescription
	 * <table>
	 * <tr>
	 *    <td><b>code</b></td>
	 *    <td><b>msg</b></td>
	 * </tr>
	 * <tr>
	 *    <td>null</td>
	 *    <td>成功</td>
	 * </tr>
	 * <tr>
	 *    <td>1000</td>
	 *    <td>系统错误</td>
	 * </tr>
	 * <tr>
	 *    <td>1001</td>
	 *    <td>未登录或者无权限</td>
	 * </tr>
	 * <tr>
	 *    <td>1002</td>
	 *    <td>参数验证错误</td>
	 * </tr>
	   <tr>
	     <td>1003</td>
	     <td>入参json解析异常</td>
	    </tr>
	    <tr>
	     <td>1004</td>
	     <td>数据排他错误，请刷新后重新提交</td>
	    </tr>
	   <tr>
	     <td>1005</td>
	     <td>排序字段包含非法字符</td>
	   </tr>
	   <tr>
	     <td>1006</td>
	     <td>萤石云异常</td>
	   </tr>
	   <tr>
	     <td>1007</td>
	     <td>导入模板异常</td>
	   </tr>
	   <tr>
	     <td>1008</td>
	     <td>json解析异常</td>
	   </tr>
	   <tr>
	     <td>1009</td>
	     <td>参数绑定异常</td>
	   </tr>
	   <tr>
	     <td>1010</td>
	     <td>查询详细或者修改的时候未找到数据</td>
	   </tr>
	   <tr>
	     <td>1011</td>
	     <td>关键字段重复</td>
	   </tr>
	   <tr>
	     <td>1012</td>
	     <td>合法性验证错误</td>
	   </tr>
	   
	 * </table>
	 */
	
	/**
	 * @api {GET} /list 分页信息
	 * @apiGroup common
	 * @apiName  list
	 * 
	 * @apiParam {Number} pageNo 页数 
	 * @apiParam {Number} pageSize 每页显示多少条
	 * @apiParam {String} orderBy 排序字段
	 * @apiParam {String} orderType 排序类型(ASC,DESC)
	 * 
	 * @apiSuccess {Number} pageNo 页数
	 * @apiSuccess  {Number} pageSize  
	 * @apiSuccess  {Number} count 总记录数
	 * 
	 * @apiSuccessExample {json} Request-Example:
	 * {
    		"pageNo": 1,
    		"pageSize": 10,
    		"count": 111052,
    		"orderBy": "id",
    		"orderType": "ASC",
    		"total": 111052,
    		"firstResult": 0,
    		"list": [..]
    	}
	 */
}
