package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.TeacherSchoolMap;

public interface TeacherSchoolMapMapper  {
	
	/**
	 * 
	 *
	 * 方法描述: [根据老师和学校id搜索]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月27日-下午7:26:06<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param schoolId
	 * @param teacherId
	 * @return
	 * TeacherSchoolMap
	 *
	 */
	TeacherSchoolMap selectBySchoolIdAndTeacherId(@Param(value="schoolId")Long schoolId,@Param(value="teacherId")Long teacherId);
	
	/**
	 * 
	 * 方法描述: [搜索学校内的园长的id]</br>
	 * 初始作者: wangshaojie<br/> 
	 * 创建日期: 2017年7月28日-上午10:09:12<br/> 
	 * 开始版本: 2.0.0<br/> 
	 * =================================================<br/>
	 * 修改记录：<br/>
	 * 修改作者         日期         修改内容<br/>
	 * ================================================<br/>
	 * @param schoolId
	 * @return
	 * List<Long>
	 *
	 */
	List<Long> selectDirectorIdBySchoolId(Long schoolId);
}