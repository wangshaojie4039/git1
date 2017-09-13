package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.imexue.ec.common.model.SchoolNoticeReceiver;
import cn.imexue.ec.common.model.vo.SchoolNoticeReceiverVo;

public interface SchoolNoticeReceiverMapper {

	int deleteByPrimaryKey(Long id);

	int insert(SchoolNoticeReceiver record);

	int insertSelective(SchoolNoticeReceiver record);

	SchoolNoticeReceiver getById(Long id);

	int update(SchoolNoticeReceiver record);

	List<SchoolNoticeReceiverVo> getBySchoolNoticeIdAndIsRead(@Param("schoolId") Long schoolId,
			@Param("schoolNoticeId") Long schoolNoticeId,
			@Param("isRead") Integer isRead);

	SchoolNoticeReceiver getByNoticeIdAndUid(@Param("schoolId") Long schoolId, @Param("schoolNoticeId") Long schoolNoticeId, @Param("receiverId") Long uid,
			@Param("role") String role);
}
