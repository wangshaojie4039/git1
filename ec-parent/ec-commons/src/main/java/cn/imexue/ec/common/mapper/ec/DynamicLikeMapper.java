package cn.imexue.ec.common.mapper.ec;

import java.util.List;


public interface DynamicLikeMapper  {

	
	List<String> selectUserNameByDynamicId(Long dynamicId);
}