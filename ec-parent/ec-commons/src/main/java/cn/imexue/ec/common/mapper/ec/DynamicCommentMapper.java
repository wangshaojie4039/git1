package cn.imexue.ec.common.mapper.ec;

import java.util.List;

import cn.imexue.ec.common.model.DynamicComment;


public interface DynamicCommentMapper  {

	/**
	 * 根据动态id搜索
	 * @param dynamicId 动态id
	 * @return
	 */
	List<DynamicComment> selectByDynamicId(Long dynamicId);
}