package cn.imexue.ec.web.service.cache;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames="VerifyLoginCache")
public class VerifyCacheService {

	@CachePut(key="#key")
	public String setValue(String key){
		return "1";
	}
	
	@Cacheable(key="#key",sync=true)
	public String getValue(String key){
		return null;
	}
	
}
