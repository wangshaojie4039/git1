package cn.imexue.ec.web.web.controller.school;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.model.Teacher;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.model.vo.DownloadUrl;
import cn.imexue.ec.web.service.school.TeacherService;
import cn.imexue.ec.web.web.controller.school.req.TeacherExcel;
import cn.imexue.ec.web.web.controller.school.req.TeacherQuery;
import cn.imexue.ec.web.web.controller.school.req.TeacherReq;
import cn.imexue.ec.web.web.excel.ExcelUtils;
import cn.imexue.ec.web.web.role.Role;
import cn.imexue.ec.web.web.role.RoleType;

@RestController
@Role(RoleType.D)
@RequestMapping("teacher")
public class TeacherController {

	@Resource
	private TeacherService teacherService;
	
	private static final String importingXls = "importingXls";
	
	/**
	 * @api {POST} /teacher/list 教师列表
	 * @apiGroup teacher
	 * @apiName teacherList
	 * 
	 * @apiParam {String[..11]} mobile 手机号
	 * 
	 * @apiSuccess {Number} sex 1男2女其他为未知
	 * @apiSuccess {Number} isMaster 1班主任2老师
	 * @apiSuccess {Number} hasLogin 1安装其他未按照
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * {
        "id": 106,
        "versionNo": 1,
        "mobile": "17150511427",
        "name": "超级园长",
        "sex": 1,
        "birthday": "1520-10-10 00:00:00",
        "hasLogin": 1,
        "isActive": 1,
        "logoUrl": "image/2017/05/18/37cb18e2-a6a0-495b-a6cd-3961daf9fe5b.png",
        "role": "D",
        "schoolId": 1048,
        "classes": [
          {
            "id": 100048,
            "versionNo": null,
            "schoolId": 1048,
            "name": "星星一班",
            "schoolYear": "2017",
            "masterTeacherId": 106,
            "lastPromoteTime": "2017-04-14 14:12:45",
            "isGraduate": null,
            "graduateTime": null,
            "isDelete": null,
            "deleteTime": null,
            "isMaster": 1,
            "master": null,
            "teachers": null
          },
        ]
      },
	 * 
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public RespJson list(Page<TeacherVo> page,@RequestBody TeacherQuery query){
		page.checkOrder(TeacherVo.class);
		Long schoolId = LoginUtil.getSchoolId();
		page.getSearch().put("schoolId", schoolId);
		teacherService.pageList(page.getSearch());
		
		return RespJsonFactory.buildSuccess(page);
	}
	
	/**
	 * @api {GET} /teacher/get 教师详细信息
	 * @apiGroup teacher
	 * @apiName teacherget
	 * 
	 * @apiParam {Number} teacherId
	 * 
	 * @apiError 1010 未找到老师
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 	{
		  "result": 1,
		  "code": null,
		  "msg": "成功",
		  "data": {
		    "id": 10,
		    "versionNo": 1,
		    "mobile": "13939676471",
		    "createTime": "2017-03-06 17:20:16",
		    "name": "天才赵",
		    "sex": null,
		    "birthday": "1987-01-01 00:00:00",
		    "hasLogin": 1,
		    "isActive": 1,
		    "logoUrl": "image/2017/06/02/82c10876-2f88-43db-bea1-35b6417c101a.png",
		    "role": null,
		    "schoolId": 1048,
		    "classes": null
		  }
		}
	 * 
	 */
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public RespJson get(@PathVariable("id") Long teacherId){
		
		Assert.notNull(teacherId,"teacherId不能为空");
		Long schoolId = LoginUtil.getSchoolId();
		
		TeacherVo teacherVo = teacherService.getTeacherById(teacherId, schoolId);
		return RespJsonFactory.buildSuccess(teacherVo);
	}
	
	/**
	 * @api {GET} /teacher/getByMobile/{mobile} 根据手机号获取教师信息
	 * @apiGroup teacher
	 * @apiName teachergetByMobile
	 * @apiDescription 根据手机号获取教师信息,如果为null表示该手机号可以注册,
	 * 		如果有数据则把数据拉取到页面编辑
	 * 
	 * @apiParam {Number} mobile
	 * 
	 * @apiError 1011 老师手机号重复
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * 	{
		  "result": 1,
		  "code": null,
		  "msg": "成功",
		  "data": {
		    "id": 10,
		    "versionNo": 1,
		    "mobile": "13939676471",
		    "name": "天才赵",
		    "sex": null,
		    "birthday": "1987-01-01 00:00:00",
		    "hasLogin": 1,
		    "isActive": 1,
		    "logoUrl": "image/2017/06/02/82c10876-2f88-43db-bea1-35b6417c101a.png",
		    "role": null,
		    "schoolId": 1048,
		    "classes": null
		  }
		}
	 * 
	 */
	@RequestMapping(value="getByMobile/{mobile}",method=RequestMethod.GET)
	public RespJson getByMobile(@PathVariable("mobile")String mobile){
		
		Assert.notNull(mobile,"电话不能为空");
		if(mobile.length()!=11){
			throw new AppChkException(3003, "teacher.mobile.length.err",mobile.length());
		}
		
		Long schoolId = LoginUtil.getSchoolId();
		TeacherVo teacher = teacherService.getTeacherByMobile(mobile, schoolId);
		teacher.setMobile(mobile);
		return RespJsonFactory.buildSuccess(teacher);
	}
	
	/**
	 * @api {GET} /teacher/remove 从幼儿园移除教师
	 * @apiGroup teacher
	 * @apiName teacherremove
	 * 
	 * @apiParam {Number} teacherId
	 */
	@RequestMapping(value="remove/{id}",method=RequestMethod.GET)
	public RespJson remove(@PathVariable("id")Long teacherId){
		
		Assert.notNull(teacherId,"teacherId不能为空");
		Long schoolId = LoginUtil.getSchoolId();
		
		teacherService.remove(teacherId, schoolId);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} /teacher/save 保存教师信息
	 * @apiGroup teacher
	 * @apiName teacherSave
	 * @apiDescription 如果id存在则新增，如果id存在则修改
	 * 
	 * @apiParamExample {json} Request-Example:
	 * {
	 * 	  "id":10,
		  "birthday": "1987-01-01 00:00:00",
		  "logoUrl": "string",
		  "mobile": "13939676472",
		  "name": "string",
		  "role": "D",
		  "sex": 2,
		  "versionNo": 2
		}
	 * 
	 * 
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public RespJson save(@RequestBody @Valid TeacherReq teacher){
		Teacher teacher2 = new Teacher();
		BeanUtils.copyProperties(teacher, teacher2);
		
		Long schoolId = LoginUtil.getSchoolId();
		
		teacherService.save(teacher2, teacher.getRole(), schoolId,false,null);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} /teacher/query 查询教师信息教师信息
	 * @apiGroup teacher
	 * @apiName teacherQuery
	 * @apiDescription 模糊查询教师的手机号或者姓名
	 *
	 * @apiSuccessExample {json} Success-Response:
	 * {参考getByMobile}
	 */
	@Role
	@RequestMapping(value="query",method=RequestMethod.GET)
	public RespJson query(){
		
		Long schoolId = LoginUtil.getSchoolId();
//		List<TeacherVo> list = teacherService.query((String)query.getSearch().get("username"), schoolId);
		List<TeacherVo> list = teacherService.query(null, schoolId);
		
		return RespJsonFactory.buildSuccess(list);
	}
	
	/**
	 * @api {GET} teacher/getUploadUrl 获取幼儿模板和上传路径
	 * @apiGroup teacher
	 * @apiName teacherGetUploadUrl
	 * 
	 * @apiSuccessExample {json} Success-response:
	 *  {
		    "templateUrl": "/老师信息导入模板.xls",
		    "uploadUrl": "teacher/importingXls"
	  	}
	 */
	@RequestMapping(value="getUploadUrl",method=RequestMethod.GET)
	public RespJson getUploadUrl(){
		
		DownloadUrl url = new DownloadUrl();
		url.setTemplateUrl("/老师信息导入模板.xls");
		url.setUploadUrl("teacher/"+importingXls);
		
		return RespJsonFactory.buildSuccess(url);
	}
	
	/**
	 * @api {POST} teacher/importingXls 上传教师文件
	 * @apiGroup teacher
	 * @apiName teacherImportingXls
	 */
	@RequestMapping(value=importingXls,method=RequestMethod.POST)
	public RespJson importingXls(MultipartFile file){
		if(file==null){
			return RespJsonFactory.buildFailure("文件为空");
		}
		List<TeacherExcel> excelUpload = ExcelUtils.excelUpload(file, TeacherExcel.class, 3);
		Long schoolId = LoginUtil.getSchoolId();
		teacherService.importTeacher(excelUpload, schoolId);
		
		return RespJsonFactory.buildSuccess(file.getOriginalFilename());
	}
	
}
