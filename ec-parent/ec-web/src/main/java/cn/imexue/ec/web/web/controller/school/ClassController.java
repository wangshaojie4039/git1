package cn.imexue.ec.web.web.controller.school;

import java.util.ArrayList;
import java.util.Calendar;
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

import cn.imexue.ec.common.exception.DataNoFountException;
import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.model.page.PageQuery;
import cn.imexue.ec.common.model.vo.ClassVo;
import cn.imexue.ec.common.model.vo.TeacherVo;
import cn.imexue.ec.common.resp.RespJson;
import cn.imexue.ec.common.resp.RespJsonFactory;
import cn.imexue.ec.common.util.login.LoginUtil;
import cn.imexue.ec.web.service.school.ClassService;
import cn.imexue.ec.web.web.controller.school.req.ClassReq;
import cn.imexue.ec.web.web.role.Role;


@Role
@RestController
@RequestMapping("class")
public class ClassController {

	@Resource
	private ClassService classService;
	
	/**
	 * @throws Exception 
	 * @api {POST} /class/list 班级列表
	 * @apiGroup class
	 * @apiName classList
	 * 
	 * @apiSuccess {String} name 班级名称
	 * @apiSuccess {Date} lastPromoteTime 升班日期
	 * @apiSuccess {Object} master 班主任
	 * @apiSuccess {Array} teachers 教师（和班主任格式相同）
	 * @apiSuccess {Number} childNum 幼儿数量
	 * @apiSuccess {Number} parentNum 家长数量 
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * {
	      "id": 29,
	      "versionNo": null,
	      "schoolId": 14,
	      "name": "小一班",
	      "schoolYear": "2017",
	      "masterTeacherId": 28,
	      "lastPromoteTime": "2017-03-10 17:54:10",
	      "isGraduate": null,
	      "graduateTime": null,
	      "isDelete": null,
	      "deleteTime": null,
	      "childNum": 0,
	      "parentNum": 0,
	      "master": {
	        "id": 28,
	        "versionNo": null,
	        "mobile": "15824569521",
	        "uid": null,
	        "uuid": null,
	        "name": "访问服务   666",
	        "sex": null,
	        "birthday": null,
	        "hasLogin": null,
	        "isActive": null,
	        "logoUrl": null,
	        "role": null,
	        "schoolId": null,
	        "classes": null
	      },
	      "teachers": [],
	      "teachersName":"",
	    }
	 * 
	 */
	@RequestMapping(value="list",method=RequestMethod.POST)
	public RespJson list(Page<ClassVo> page,@RequestBody PageQuery query) throws Exception{
		page.checkOrder(ClassVo.class);
		//如果不是园长则查询该教师的班级
		Long teacherId = null;
		if(LoginUtil.getLoginInfo().getUserRole().equals("T")){
			teacherId = LoginUtil.getLoginId();
		}
		page.getSearch().put("teacherId", teacherId);
		classService.pageList(page.getSearch());
		
		return RespJsonFactory.buildSuccess(page);
	}
	
	/**
	 * @api {POST} /class/get/{id} 班级详细
	 * @apiGroup class
	 * @apiName classGet
	 * 
	 */
	@RequestMapping(value="get/{id}",method=RequestMethod.GET)
	public RespJson get(@PathVariable("id") Long id){
		
		Long schoolId = LoginUtil.getSchoolId();
		ClassVo clazz = classService.getById(id);
		//判断班级是否属于学校
		if(!clazz.getSchoolId().equals(schoolId)){
			throw new DataNoFountException("班级");
		}
		
		return RespJsonFactory.buildSuccess(clazz);
	}
	
	/**
	 * @api {POST} /class/save 保存班级
	 * @apiGroup class
	 * @apiName classSave
	 * 
	 * @apiParam {Number} id 
	 * @apiParam {Number} masterId 班主任id
	 * @apiParam {String} name 
	 * @apiParam {Array} teachers 老师id
	 * 
	 * @apiError 1011  班级名称重复
	 * 
	 * @apiParamExample {json} Request-Example:
	 * {
		  "id": 30,
		  "masterId": 28,
		  "name": "string",
		  "teacherIds": [
		    28,7
		  ],
		  "versionNo": 2
		}
	 *
	 * 
	 */
	@RequestMapping(value="save",method=RequestMethod.POST)
	public RespJson save(@RequestBody @Valid ClassReq classReq){
		ClassVo clazz = new ClassVo();
		BeanUtils.copyProperties(classReq, clazz,"masterId","teachers");
		
		Long schoolId = LoginUtil.getSchoolId();
		clazz.setSchoolId(schoolId);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		clazz.setSchoolYear(""+year);
		clazz.setMasterTeacherId(classReq.getMasterId());
		List<Long> teachers = classReq.getTeacherIds();
		clazz.setTeachers(new ArrayList<TeacherVo>());
		if(teachers != null){
			for(Long l : teachers){
				TeacherVo t = new TeacherVo();
				t.setId(l);
				clazz.getTeachers().add(t);
			}
		}
		classService.save(clazz,false);
		
		return RespJsonFactory.buildSuccess();
	}
	
	
	/**
	 * @api {POST} /class/promote 升班
	 * @apiGroup class
	 * @apiName classPromote
	 * 
	 * @apiError 1011  班级名称重复
	 * 
	 * @apiParam {String} name 新的班级名称
	 * @apiParam {Number} id 
	 * 
	 */
	@RequestMapping(value="promote",method=RequestMethod.POST)
	public RespJson promote(@RequestBody ClassReq classReq){
		Long id = classReq.getId();
		String name = classReq.getName();
		Integer versionNo = classReq.getVersionNo();
		Long schoolId = LoginUtil.getSchoolId();
		Assert.notNull(id, "班级名不能为空");
		Assert.notNull(name, "班级名不能为空");
		Assert.notNull(versionNo, "版本不能为空");
		
		ClassVo clazz = new ClassVo();
		clazz.setId(id);
		clazz.setName(name);
		clazz.setVersionNo(versionNo);
		clazz.setSchoolId(schoolId);
		
		classService.promote(clazz);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} /class/graduate 毕业
	 * @apiGroup class
	 * @apiName classGraduate
	 * 
	 * @apiParam {Number} id 
	 * 
	 */
	@RequestMapping(value="graduate",method=RequestMethod.POST)
	public RespJson graduate(@RequestBody ClassReq classReq){
		Long id = classReq.getId();
		Integer versionNo = classReq.getVersionNo();
		Long schoolId = LoginUtil.getSchoolId();
		Assert.notNull(id, "班级名不能为空");
		Assert.notNull(versionNo, "版本不能为空");
		
		ClassVo clazz = new ClassVo();
		clazz.setId(id);
		clazz.setVersionNo(versionNo);
		clazz.setSchoolId(schoolId);
		
		classService.graduate(clazz);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} /class/dismiss 解散
	 * @apiGroup class
	 * @apiName classDismiss
	 * 
	 * @apiParam {Number} id 
	 * 
	 */
	@RequestMapping(value="dismiss",method=RequestMethod.POST)
	public RespJson dismiss(@RequestBody ClassReq classReq){
		Long id = classReq.getId();
		Integer versionNo = classReq.getVersionNo();
		Long schoolId = LoginUtil.getSchoolId();
		Assert.notNull(id, "班级名不能为空");
		Assert.notNull(versionNo, "版本不能为空");
		
		ClassVo clazz = new ClassVo();
		clazz.setId(id);
		clazz.setVersionNo(versionNo);
		clazz.setSchoolId(schoolId);
		
		classService.dismiss(clazz,true);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {POST} /class/dismissAndDelete 解散并删除
	 * @apiGroup class
	 * @apiName classDismissAndDelete
	 * 
	 * @apiParam {Number} id 
	 * 
	 */
	@RequestMapping(value="dismissAndDelete",method=RequestMethod.POST)
	public RespJson dismissAndDelete(@RequestBody ClassReq classReq){
		Long id = classReq.getId();
		Integer versionNo = classReq.getVersionNo();
		Long schoolId = LoginUtil.getSchoolId();
		Assert.notNull(id, "班级id不能为空");
		Assert.notNull(versionNo, "版本不能为空");
		
		ClassVo clazz = new ClassVo();
		clazz.setId(id);
		clazz.setVersionNo(versionNo);
		clazz.setSchoolId(schoolId);
		clazz.setIsDelete((byte)1);
		classService.dismissAndDelete(clazz);
		
		return RespJsonFactory.buildSuccess();
	}
	
	/**
	 * @api {GET} /class/allList 获取所有班级
	 * @apiGroup class
	 * @apiName classAllList
	 * 
	 * @apiSuccessExample {json} Success-Response:
	 * {参考list}
	 */
	@Role
	@RequestMapping(value="allList",method=RequestMethod.GET)
	public RespJson allList(){
		
		Long schoolId = LoginUtil.getSchoolId();
		//如果不是园长则查询该教师的班级
		Long teacherId = null;
		if(LoginUtil.getLoginInfo().getUserRole().equals("T")){
			teacherId = LoginUtil.getLoginId();
		}
		List<ClassVo> list = classService.getList(schoolId,teacherId);
		
		return RespJsonFactory.buildSuccess(list);
	}
	
}
