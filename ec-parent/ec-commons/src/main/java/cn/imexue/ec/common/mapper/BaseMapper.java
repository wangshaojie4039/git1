package cn.imexue.ec.common.mapper;

import java.util.List;
import java.util.Map;

import cn.imexue.ec.common.model.common.BaseEntity;

public interface BaseMapper<T extends BaseEntity> {

	List<T> pageList(Map<String, Object> param);

	T getById(Long id);

	int update(T t);

	int insert(T t);

	int deleteById(Long id);
}
