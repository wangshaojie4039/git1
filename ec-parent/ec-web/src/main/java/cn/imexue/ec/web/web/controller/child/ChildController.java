package cn.imexue.ec.web.web.controller.child;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.imexue.ec.common.model.SysCode;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.CardVo;
import cn.imexue.ec.common.model.vo.ChildVo;
import cn.imexue.ec.common.model.vo.ParentVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.service.sys.SysCodeService;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.DownloadUrl;
import cn.imexue.ec.web.service.child.ChildCardService;
import cn.imexue.ec.web.service.child.ChildService;
import cn.imexue.ec.web.service.child.ParentService;
import cn.imexue.ec.web.web.controller.child.req.ChildCardExcel;
import cn.imexue.ec.web.web.controller.child.req.ChildCardReq;
import cn.imexue.ec.web.web.controller.child.req.ChildExcel;
import cn.imexue.ec.web.web.controller.child.req.ChildParentReq;
import cn.imexue.ec.web.web.controller.child.req.ChildQuery;
import cn.imexue.ec.web.web.controller.child.req.ChildReq;
import cn.imexue.ec.web.web.controller.child.req.ParentReq;
import cn.imexue.ec.web.web.excel.ExcelException;
import cn.imexue.ec.web.web.excel.ExcelUtils;
import cn.imexue.ec.web.web.role.Role;

@Role
@RestController
@RequestMapping("child")
public class ChildController {

	private static final String CHILD_UPLOAD_URL = "importingXls";
	
	private static final String CHILD_CARD_UPLOAD_URL = "importingCardXls";
	
	@Resource
	private ChildService childService;
	
	@Resource
	private ParentService parentService;
	
	@Resource
	private ChildCardService childCardService;
	
	/**
	 * @api {POST} child/list 幼儿列表
	 * @apiGroup child
	 * @apiName childList
	 * 
	 * @apiParam {Number} classId 班级id
	 * @apiParam {String} name 幼儿姓名(模糊搜索)
	 * 
	 * @apiSuccess {Number} sex 1男2女
	 * @apiSuccessExample {json} Success-response:
	 * {
	      "id": 2601,
	      "versionNo": 1,
	      "schoolId": 14,
	      "classId": 131,
	      "name": "张小花2601",
	      "logoUrl": null,
	      "sex": 1,
	      "birthday": "2015-01-22 00:00:00",
	      "idCardNo": null,
	      "isGraduate": null,
	      "graduateTime": null,
	      "isDelete": null,
	      "deleteTime": null,
	      "cardNum": 0,
	      "className": "大九班",
	      "reladtionShipName": null,
	      "parents": [
	        {
	          "id": 9,
	          "versionNo": 1,
	          "mobile": "18014004855",
	          "uid": null,
	          "uuid": null,
	          "name": "刘苹果爸爸1",
	          "sex": 1,
	          "birthday": null,
	          "isActive": 1,
	          "relationName": "爸爸",
	          "relationCode": "BB",
	          "children": null
	        }
	      ]
	    },
	 * 
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public RespJson list(Page<ChildVo> page,@RequestBody ChildQuery query){
		page.checkOrder(ChildVo.class);
		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		Long loginId = LoginUtil.getLoginId();
		String role = LoginUtil.getLoginInfo().getUserRole();
		if("T".equals(role)){
			page.getSearch().put("teacherId", loginId);
		}
		childService.list(page.getSearch());
		
		return RespJsonFactory.buildSuccess(page);
	}
	
	/**
	 * @api {GET} child/get/{id} 幼儿详情
	 * @apiGroup child
	 * @apiName childGet
	 * 
	 * @apiParam {Number} id 幼儿id
	 * 
	 * @apiSuccessExample {json} Success-response:
	 * {参考list}
	 */
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public RespJson get(@PathVariable("id")Long id){
		
		Long schoolId = LoginUtil.getSchoolId();
		ChildVo child = childService.get(id, schoolId);
		
		return RespJsonFactory.buildSuccess(child);
	}
	
	/**
	 * @api {GET} child/removeChild/{id} 移除幼儿
	 * @apiGroup child
	 * @apiName childRemoveChild
	 * 
	 * @apiParam {Number} id 幼儿id
	 * 
	 * @apiError 3013 没有找到幼儿
	 * 
	 */
	@RequestMapping(value="removeChild/{id}",method=RequestMethod.GET)
	public RespJson removeChild(@PathVariable("id")Long id){
		
		Long schoolId = LoginUtil.getSchoolId();
		childService.removeChild(id, schoolId);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} child/getParentByMobile 根据手机号搜索家长
	 * @apiGroup child
	 * @apiName childGetParentByMobile
	 * 
	 * @apiParam {Number} childId
	 * @apiParam {Number} mobile 
	 * 
	 * @apiError 3020 家长已在其他学校注册
	 */
	@RequestMapping(value="getParentByMobile",method=RequestMethod.POST)
	public RespJson getParentByMobile(@RequestBody ParentReq req){
		String mobile = req.getMobile();
		Long childId = req.getChildId();
		Long schoolId = LoginUtil.getSchoolId();
		ParentVo parentVo = parentService.getParentByMobile(childId,mobile,schoolId);
		if(parentVo==null){
			parentVo = new ParentVo();
			parentVo.setMobile(mobile);
		}
		return RespJsonFactory.buildSuccess(parentVo);
	}
	
	/**
	 * @api {POST} child/save 保存幼儿
	 * @apiGroup child
	 * @apiName childSave
	 * 
	 * @apiParam {Number} id 新增则不需要
	 * @apiParam {String} name 姓名
	 * @apiParam {String} logoUrl logo地址
	 * @apiParam {Number} classId 班级id
	 * @apiParam {Number} sex 1男2女
	 * @apiParam {Date} birthday yyyy-MM-dd hh:mm:ss
	 * @apiParam {String[18]} idCardNo 身份证
	 * @apiParam {Number} versionNo
	 * 
	 * @apiError 3012 学生名称重复
	 * 
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public RespJson save(@RequestBody @Valid ChildReq req){
		ChildVo child = new ChildVo();
		BeanUtils.copyProperties(req, child);
		Long schoolId = LoginUtil.getSchoolId();
		child.setSchoolId(schoolId);
		childService.save(child, false);
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} child/getParent/{id} 获取单个幼儿父母
	 * @apiGroup child
	 * @apiName childGetParent
	 * 
	 * @apiParam {Number} id 家长id 
	 * 
	 * 
	 */
	@RequestMapping(value="getParent/{id}",method=RequestMethod.GET)
	public RespJson getParent(@PathVariable("id")Long id){
		
		Long schoolId = LoginUtil.getSchoolId();
		ParentVo parentVo = parentService.getParent(id, schoolId);
		
		return RespJsonFactory.buildSuccess(parentVo);
	}
	
	/**
	 * @api {GET} child/parents/{id} 获取幼儿父母
	 * @apiGroup child
	 * @apiName childParents
	 * 
	 * @apiParam {Number} id 幼儿id
	 */
	@RequestMapping(value="parents/{id}",method=RequestMethod.GET)
	public RespJson parents(@PathVariable("id")Long id){
		
		List<ParentVo> parentList = parentService.parentList(id);
		
		return RespJsonFactory.buildSuccess(parentList);
	}
	
	/**
	 * @api {POST} child/removeParent 移除幼儿父母关系
	 * @apiGroup child
	 * @apiName childRemoveParent
	 * 
	 * @apiParam {Number} childId 幼儿id
	 * @apiParam {String[11]} parentId 家长id
	 * 
	 * 
	 */
	@RequestMapping(value="removeParent",method=RequestMethod.POST)
	public RespJson removeParent(@RequestBody @Valid ChildParentReq req){
		
		parentService.removeParentChildRelationShip(req.getParentId(), req.getChildId());
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} child/saveParent 保存幼儿父母
	 * @apiGroup child
	 * @apiName childSaveParent
	 * 
	 * @apiParam {Number} childId 幼儿id
	 * @apiParam {String} mobile 手机号
	 * @apiParam {String} name 家长姓名
	 * @apiParam {String} relationCode 关系code
	 * @apiParam {String} relationName 如果code为OTHER这里才能有值
 	 * 
 	 * @apiError 1011 没找到幼儿
 	 * @apiError 3014 无效的关系码
 	 * @apiError 3015 家长被禁用
 	 * @apiError 3020 家长已在其他学校注册
 	 * @apiError 3021 家长已绑定该幼儿
	 */
	@RequestMapping(value="saveParent",method=RequestMethod.POST)
	public RespJson saveParent(@RequestBody @Valid ParentReq req){
		
		Long schoolId = LoginUtil.getSchoolId();
		ParentVo parent = new ParentVo();
		BeanUtils.copyProperties(req, parent);
		parent.setName(req.getParentName());
		Long childId = req.getChildId();
		
		parentService.addParent(parent, childId, schoolId,null);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} child/cards/{id} 幼儿卡列表
	 * @apiGroup child
	 * @apiName childCards
	 * 
	 * @apiParam {Number} id 幼儿id
	 * 
	 */
	@RequestMapping(value="cards/{id}",method=RequestMethod.GET)
	public RespJson cards(@PathVariable("id")Long id){
		
		Long schoolId = LoginUtil.getSchoolId();
		List<CardVo> list = childCardService.list(id, schoolId);
		return RespJsonFactory.buildSuccess(list);
	}
	
	/**
	 * @api {POST} child/dismiss/ 解绑幼儿卡
	 * @apiGroup child
	 * @apiName childDismiss
	 * 
	 * @apiParam {Number} cardId 卡id
	 * @apiParam {Number} versionNo 版本号
	 * 
	 */
	@RequestMapping(value="dismiss",method=RequestMethod.POST)
	public RespJson dismiss(@RequestBody ChildCardReq req){
		Assert.notNull(req.getCardId(),"卡id不能为null");
		Assert.notNull(req.getVersionNo(),"版本号不能为null");
		CardVo card = new CardVo();
		card.setId(req.getCardId());
		card.setVersionNo(req.getVersionNo());
		childCardService.dismiss(card);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} child/saveCard 保存幼儿卡
	 * @apiGroup child
	 * @apiName childSaveCard
	 * 
	 * @apiParam {String} cardNo 幼儿卡编号
	 * @apiParam {Number} childId 幼儿id
	 */
	@RequestMapping(value="saveCard",method=RequestMethod.POST)
	public RespJson saveCard(@RequestBody @Valid ChildCardReq req){
		
		Long schoolId = LoginUtil.getSchoolId();
		childCardService.save(req.getCardNo(), req.getChildId(), schoolId);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} child/getUploadUrl 获取幼儿模板和上传路径
	 * @apiGroup child
	 * @apiName childGetUploadUrl
	 * 
	 * @apiSuccessExample {json} Success-response:
	 *  {
		    "templateUrl": "/幼儿信息导入模板.xls",
		    "uploadUrl": "child/importingXls"
	  	}
	 */
	@RequestMapping(value="getUploadUrl",method=RequestMethod.GET)
	public RespJson getUploadUrl(){
		
		DownloadUrl url = new DownloadUrl();
		url.setTemplateUrl("/幼儿信息导入模板.xls");
		url.setUploadUrl("child/"+CHILD_UPLOAD_URL);
		
		return RespJsonFactory.buildSuccess(url);
	}
	
	/**
	 * @api {POST} child/importingXls 上传幼儿文件
	 * @apiGroup child
	 * @apiName childImportingXls
	 */
	@RequestMapping(value=CHILD_UPLOAD_URL,method=RequestMethod.POST)
	public RespJson importingXls(@RequestParam(value="file",required=false)MultipartFile file){
		if(file==null){
			return RespJsonFactory.buildFailure("文件为空");
		}
		List<ChildExcel> list = ExcelUtils.excelUpload(file, ChildExcel.class, 3);
		if(CollectionUtils.isEmpty(list)){
			throw new ExcelException("excel.no.data");
		}
		Long schoolId = LoginUtil.getSchoolId();
		childService.importChildren(list, schoolId);
		
		return RespJsonFactory.buildSuccess(file.getOriginalFilename());
	}
	
	/**
	 * @api {GET} child/getCardUploadUrl 获取幼儿卡模板和上传路径
	 * @apiGroup child
	 * @apiName childGetCardUploadUrl
	 */
	@RequestMapping(value="getCardUploadUrl",method=RequestMethod.GET)
	public RespJson getCardUploadUrl(){
		
		DownloadUrl url = new DownloadUrl();
		url.setTemplateUrl("/幼儿考勤卡信息导入模板.xls");
		url.setUploadUrl("child/"+CHILD_CARD_UPLOAD_URL);
		
		return RespJsonFactory.buildSuccess(url);
	}
	
	/**
	 * @api {POST} child/importingCardXls 上传幼儿卡文件
	 * @apiGroup child
	 * @apiName childImportingCardXls
	 * 
	 * @apiSuccessExample {json} Success-response:
	 *  {
		    "templateUrl": "/幼儿考勤卡导入模板.xls",
		    "uploadUrl": "child/importingCardXls"
	  	}
	 * 
	 */
	@RequestMapping(value=CHILD_CARD_UPLOAD_URL,method=RequestMethod.POST)
	public RespJson importingCardXls(@RequestParam("file")MultipartFile file){
		if(file==null){
			return RespJsonFactory.buildFailure("文件为空");
		}
		List<ChildCardExcel> list = ExcelUtils.excelUpload(file, ChildCardExcel.class, 3);
		if(CollectionUtils.isEmpty(list)){
			throw new ExcelException("excel.no.data");
		}
		Long schoolId = LoginUtil.getSchoolId();
		childCardService.importChildCard(list, schoolId);
		return RespJsonFactory.buildSuccess(file.getOriginalFilename());
	}
	
	/**
	 * @api {GET} /child/getRelationCode 幼儿和家长关系
	 * @apiGroup child
	 * @apiName childGetRelationCode
	 * 
	 */
	@RequestMapping(value="getRelationCode",method=RequestMethod.GET)
	public RespJson getRelationCode(){
		List<SysCode> list = SysCodeService.getChildRelList();
		return RespJsonFactory.buildSuccess(list);
	}
	
	/**
	 * @api {POST} /child/setDefault 设置默认家长
	 * @apiGroup child
	 * @apiName childSetDefault
	 * 
	 * @apiParam {Number} childId 幼儿id
	 * @apiParam {Number} parentId 家长id
	 * 
	 * 
	 */
	@RequestMapping(value="setDefault",method=RequestMethod.POST)
	public RespJson setDefault(@RequestBody @Valid ChildParentReq req){
		
		Long childId = req.getChildId();
		Long parentId = req.getParentId();
		parentService.setDefault(parentId, childId);
		return RespJsonFactory.buildSuccess();
	}
}
