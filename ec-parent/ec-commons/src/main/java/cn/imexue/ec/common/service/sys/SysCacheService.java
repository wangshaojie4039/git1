package cn.imexue.ec.common.service.sys;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.imexue.ec.common.mapper.ec.SysCodeMapper;
import cn.imexue.ec.common.model.SysCode;

@Service
public class SysCacheService {

	@Resource
	private SysCodeMapper sysCodeMapper;
	
	@Cacheable(value="sysCode",key="#type")
	List<SysCode> selectByType(String type){
		return sysCodeMapper.selectByType(type);
	}
	
}
