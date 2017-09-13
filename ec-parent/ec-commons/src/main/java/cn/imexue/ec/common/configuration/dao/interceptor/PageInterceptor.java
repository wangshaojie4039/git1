package cn.imexue.ec.common.configuration.dao.interceptor;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.imexue.ec.common.model.page.Page;
import cn.imexue.ec.common.util.Reflections;

@Intercepts({@Signature(type = Executor.class, method = "query",
args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageInterceptor  implements Interceptor{
	
	private static final Logger log = LoggerFactory.getLogger(PageInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		
		Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Object parameterObject = boundSql.getParameterObject();

        //获取分页参数对象
        Page<Object> page = null;
        if (parameterObject != null) {
            page = convertParameter(parameterObject, page);
        }

        

        
        
        //如果设置了分页对象，则进行分页
        if (page != null && page.getPageSize() != -1) {

        	if (StringUtils.isBlank(boundSql.getSql())){
                return null;
            }
            String originalSql = boundSql.getSql().trim();
        	
            //得到总记录数
            page.setCount(SQLHelper.getCount(originalSql, null, mappedStatement, parameterObject, boundSql, log));

            //分页查询 本地化对象 修改数据库注意修改实现
            String pageSql = SQLHelper.generatePageSql(originalSql, page);
//            if (log.isDebugEnabled()) {
//                log.debug("PAGE SQL:" + StringUtils.replace(pageSql, "\n", ""));
//            }
            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //解决MyBatis 分页foreach 参数失效 start
            if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
                MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
                Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
            }
            //解决MyBatis 分页foreach 参数失效 end
            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

            invocation.getArgs()[0] = newMs;
            Object proceed = invocation.proceed();
            page.setList((List)proceed);
            return proceed;
        }
        return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if(target instanceof Executor){
			return Plugin.wrap(target, this);
		}
		return target;
	}

	@Override
	public void setProperties(Properties properties) {
	}

	private MappedStatement copyFromMappedStatement(MappedStatement ms,
            SqlSource newSqlSource) {
		MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
				ms.getId(), newSqlSource, ms.getSqlCommandType());
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if (ms.getKeyProperties() != null) {
			for (String keyProperty : ms.getKeyProperties()) {
				builder.keyProperty(keyProperty);
			}
		}
		builder.timeout(ms.getTimeout());
		builder.parameterMap(ms.getParameterMap());
		builder.resultMaps(ms.getResultMaps());
		builder.cache(ms.getCache());
		return builder.build();
	}
	
	private static Page<Object> convertParameter(Object parameterObject, Page<Object> page) {
    	try{
    		if(parameterObject instanceof Map){
    			Map<String, Object> map =  (Map<String, Object>) parameterObject;
    			if(map.containsKey("page")){
    				return (Page<Object>) map.get(Page.PAGEKEY);
    			}else{
    				return null;
    			}
    		}else if (parameterObject instanceof Page) {
                return (Page<Object>) parameterObject;
            } else {
                return (Page<Object>)Reflections.getFieldValue(parameterObject, Page.PAGEKEY);
            }
    	}catch (Exception e) {
			return null;
		}
    }
	
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
	
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
	
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
}
