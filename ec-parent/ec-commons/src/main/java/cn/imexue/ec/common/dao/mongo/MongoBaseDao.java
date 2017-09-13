package cn.imexue.ec.common.dao.mongo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.BulkOperations;
import org.springframework.data.mongodb.core.BulkOperations.BulkMode;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoBaseDao {

	@Resource
	protected MongoTemplate mongoTemplate;
	
	public void insert(Object entity){
		mongoTemplate.insert(entity);
	}
	
	public <T> void batchInsert(List<T> list,Class<T> clazz){
		if(list==null||list.size()<1)return ;
		BulkOperations bulkOps = mongoTemplate.bulkOps(BulkMode.UNORDERED, clazz);
		bulkOps.insert(list).execute();
	}
	
	public void delete(Class<? extends Object> clazz){
		mongoTemplate.dropCollection(clazz);
	}
	
	public void save(Object entity){
		mongoTemplate.save(entity);
	}

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}
	
}
