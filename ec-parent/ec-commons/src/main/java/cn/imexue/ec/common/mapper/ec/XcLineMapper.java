package cn.imexue.ec.common.mapper.ec;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.XcLine;
import cn.imexue.ec.common.model.vo.XcLineBusVo;
import cn.imexue.ec.common.model.vo.XcLineVo;

@Mapper
public interface XcLineMapper extends BaseMapper<XcLine> {

	List<? extends XcLineVo> pageLists(Map<String, Object> param);

	List<XcLineBusVo> findAllBySchoolId(@Param("schoolId") Long schoolId);

	void batchInsert(Map<String, Object> map);

	List<XcLine> queryAllByParam(Map<String, Object> param);
}
