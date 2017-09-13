package cn.imexue.ec.common.dao.mongo.xc;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cn.imexue.ec.common.dao.mongo.MongoBaseDao;
import cn.imexue.ec.common.model.mongo.XcLineStatusRecord;

/**
 * 文件名称： cn.imexue.ec.common.dao.mongo.xc.XcLineStatusMongoDao.java<br/>
 * 初始作者： 钟小平<br/>
 * 创建日期： 2017年8月14日<br/>
 * 功能说明： 校车线路状态 <br/>
 * =================================================<br/>
 * 修改记录：<br/>
 * 修改作者 日期 修改内容<br/>
 * ================================================<br/>
 * Copyright (乐迪教育科技有限公司-幼儿事业部) 2017-2018.All rights reserved.<br/>
 */
@Repository
public class XcLineStatusMongoDao extends MongoBaseDao {

	private static final String	tableName	= "xc_line_status_record";

	public List<XcLineStatusRecord> query(Query query) {

		return mongoTemplate.find(query, XcLineStatusRecord.class, tableName);
	}

}
