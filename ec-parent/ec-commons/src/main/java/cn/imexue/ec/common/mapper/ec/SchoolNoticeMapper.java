package cn.imexue.ec.common.mapper.ec;

import org.apache.ibatis.annotations.Mapper;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.SchoolNotice;
import cn.imexue.ec.common.model.vo.SchoolNoticeVo;

@Mapper
public interface SchoolNoticeMapper extends BaseMapper<SchoolNoticeVo> {

	int updateByPrimaryKeySelective(SchoolNotice record);

}
