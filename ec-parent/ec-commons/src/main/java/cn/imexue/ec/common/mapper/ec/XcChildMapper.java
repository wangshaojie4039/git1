package cn.imexue.ec.common.mapper.ec;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.XcChild;
import cn.imexue.ec.common.model.vo.ClassRideVoItem;
import cn.imexue.ec.common.model.vo.IdNameVO;
import cn.imexue.ec.common.model.vo.XcChildInfoVo;
import cn.imexue.ec.common.model.vo.XcChildItemVo;
import cn.imexue.ec.common.model.vo.XcChildVo;

@Mapper
public interface XcChildMapper extends BaseMapper<XcChild> {

	List<XcChildVo> pageLists(Map<String, Object> map);

	void batchInsert(Map<String, Object> map);

	List<XcChildItemVo> queryChildByParam(Map<String, Object> param);

	void batchUpdate(Map<String, Object> map);

	List<XcChild> findXcChildByParam(HashMap<String, Object> param);

	List<ClassRideVoItem> queryClassBySchoolId(@Param("schoolId") Long schoolId);

	List<XcChildInfoVo> queryChildInfoByParam(Map<String, Object> param);

	List<XcChildInfoVo> queryChildInfoNeedByParam(Map<String, Object> param);

	List<XcChild> findXcChildByChildId(HashMap<String, Object> param);

	void deletByParam(Map<String, Object> param);

	List<Long> findXcChildIdByParam(Map<String, Object> param);

	List<IdNameVO> queryChildBaseByParam(Map<String, Object> param);

	void deleteXcChild(Long xcChildId);
	
}
