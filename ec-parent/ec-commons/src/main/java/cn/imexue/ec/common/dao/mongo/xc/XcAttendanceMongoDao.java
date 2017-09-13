package cn.imexue.ec.common.dao.mongo.xc;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cn.imexue.ec.common.dao.mongo.MongoBaseDao;
import cn.imexue.ec.common.model.mongo.XcChildAttendance;

@Repository
public class XcAttendanceMongoDao extends MongoBaseDao {

	private static final String tableName = "xc_child_attendance";

	public List<XcChildAttendance> query(Query query) {
		query.with(new Sort(Direction.DESC, "swipeTime"));
		return mongoTemplate.find(query, XcChildAttendance.class, tableName);
	}
}
