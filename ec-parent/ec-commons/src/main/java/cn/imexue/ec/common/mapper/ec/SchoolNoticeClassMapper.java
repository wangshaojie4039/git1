package cn.imexue.ec.common.mapper.ec;

import cn.imexue.ec.common.model.SchoolNoticeClass;

public interface SchoolNoticeClassMapper {

	int deleteByPrimaryKey(Long id);

	int insert(SchoolNoticeClass record);

	int insertSelective(SchoolNoticeClass record);

	SchoolNoticeClass selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(SchoolNoticeClass record);

	int updateByPrimaryKey(SchoolNoticeClass record);
}
