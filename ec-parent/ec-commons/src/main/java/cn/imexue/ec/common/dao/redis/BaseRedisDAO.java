package cn.imexue.ec.common.dao.redis;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

public class BaseRedisDAO<T> {

	@Resource
	RedisTemplate<String, T>	redisTemplate;

	public ValueOperations<String, T> getStringOps() {

		return redisTemplate.opsForValue();
	}

	public SetOperations<String, T> getSetOps() {

		return redisTemplate.opsForSet();
	}

	public HashOperations<String, String, T> getHashOps() {

		return redisTemplate.opsForHash();
	}

	public ListOperations<String, T> getListOps() {

		return redisTemplate.opsForList();
	}

	public ZSetOperations<String, T> getZsetOps() {

		return redisTemplate.opsForZSet();
	}

	public void delete(String key) {

		redisTemplate.delete(key);
	}

	public RedisTemplate<String, T> getRedisTemplate() {

		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<String, T> redisTemplate) {

		this.redisTemplate = redisTemplate;
	}
}
