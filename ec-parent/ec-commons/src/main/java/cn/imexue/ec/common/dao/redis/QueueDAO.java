package cn.imexue.ec.common.dao.redis;

import org.springframework.stereotype.Repository;

@Repository
public class QueueDAO extends BaseRedisDAO<Object> {
	public Long inQueue(String key, Object value) {
		return getListOps().rightPush(key, value);
	}

	public Object outQueue(String key) {
		return getListOps().leftPop(key);
	}
}
