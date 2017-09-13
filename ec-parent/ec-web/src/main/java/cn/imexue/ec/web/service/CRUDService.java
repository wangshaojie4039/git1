package cn.imexue.ec.web.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.imexue.ec.common.mapper.BaseMapper;
import cn.imexue.ec.common.model.common.BaseEntity;

@Transactional(readOnly=true)
public abstract  class  CRUDService<T extends BaseEntity,D extends BaseMapper<T>>{

	
	@Autowired
	protected D dao;
	
	public T getById(Long id) {
		return dao.getById(id);
	}
	
	public List<? extends T> pageList(Map<String, Object> param) {
		return dao.pageList(param);
	}
	
	@Transactional
	public void save(T t) {
		Assert.notNull(t,"不能为空");
		if(t.getId()==null){
			dao.insert(t);
		}else{
			T t2 = getById(t.getId());
			if(t2 == null)
				throw new RuntimeException("设备不存在");
			dao.update(t);
		}
	}
	
}
