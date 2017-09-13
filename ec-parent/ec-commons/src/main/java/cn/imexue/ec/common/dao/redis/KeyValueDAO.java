package cn.imexue.ec.common.dao.redis;

import java.util.Date;
import java.util.Set;

import org.springframework.stereotype.Repository;

@Repository
public class KeyValueDAO extends BaseRedisDAO<String> {
	public void set(String key, String value,long timeStamp) {
		getStringOps().set(key, value);
		if(timeStamp != 0){
			redisTemplate.expireAt(key, new Date(timeStamp));
		}
	}

	public String get(String key) {
		return getStringOps().get(key);
	}

	public Long sadd(String key, String... values) {
		return getSetOps().add(key, values);
	}
	
	public Long srem(String key, Object... values) {
		return getSetOps().remove(key, values);
	}
	
	public Set<String> smembers(String key) {
		return getSetOps().members(key);
	}
	
}
