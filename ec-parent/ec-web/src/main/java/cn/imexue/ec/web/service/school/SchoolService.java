package cn.imexue.ec.web.service.school;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.imexue.ec.common.mapper.ec.SchoolMapper;
import cn.imexue.ec.common.model.School;
import cn.imexue.ec.common.model.vo.SchoolVo;

@Service
@Transactional(readOnly=true)
public class SchoolService {

	@Resource
	private SchoolMapper schoolMapper;
	
	/**
	 * 根据学校id返回学校基础信息
	 * @param schoolId
	 * @return
	 */
	public SchoolVo getSchool(Long schoolId){
		SchoolVo schoolVo = schoolMapper.getSchoolById(schoolId,true);
		if(schoolVo==null){
			schoolVo = schoolMapper.getSchoolById(schoolId,null);
		}
		Assert.notNull(schoolVo,"没有数据");
		//教师数
		Integer teacherNum = schoolMapper.getTeacherNum(schoolId);
		//家长数
		Integer parentNum = schoolMapper.getParentNum(schoolId);
		//幼儿数
		Integer childrenNum = schoolMapper.getChildrenNum(schoolId);
		schoolVo.setTeacherNum(teacherNum);
		schoolVo.setChildrenNum(childrenNum);
		schoolVo.setParentNum(parentNum);
		
		return schoolVo;
	}
	
	/**
	 * 保存学校基础信息
	 * @param school
	 */
	@Transactional
	public void saveSchool(School school){
		schoolMapper.updateSchool(school);
	}
	
	@Transactional
	public void saveSetting(School school){
		schoolMapper.updateSchoolSetting(school);
	}
	
}
