package cn.imexue.ec.common.configuration.dao.interceptor;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import cn.imexue.ec.common.exception.AppChkException;
import cn.imexue.ec.common.model.common.DataEntity;
import cn.imexue.ec.common.util.login.LoginInfo;
import cn.imexue.ec.common.util.login.LoginUtil;

/**
 * 用于更新用户的创建人，创建时间等信息
 * @author hl
 * @version 2015-12-23
 */
@Intercepts({@Signature(type = Executor.class, method = "update",
        args = {MappedStatement.class, Object.class})})
public class UpdateBaseInformationInterceptor implements Interceptor {

	
	
	@Override
	@SuppressWarnings("unchecked")
    public Object intercept(Invocation invocation) throws Throwable {

        Object parameter = invocation.getArgs()[1];
        LoginInfo info = LoginUtil.getLoginInfo();
        if(info == null){
        	info = new LoginInfo(null, 0L , null, null, "SYS", 0L);
        }
        Date now = new Date();
        if(parameter instanceof DataEntity){
        	DataEntity entity = (DataEntity)parameter;
        	entity.setCreateTime(now);
        	entity.setUpdateTime(now);
        	entity.setCreatorId(info.getUserId());
        	entity.setUpdaterId(info.getUserId());
        	entity.setCreatorRole(info.getRole());
        	entity.setUpdaterRole(info.getRole());
        }
        if(parameter instanceof Map){
        	Map<String, Object> map = (Map<String, Object>)parameter;
        	map.put("createTime", now);
        	map.put("updateTime", now);
        	map.put("creatorId", info.getUserId());
        	map.put("UpdaterId", info.getUserId());
        	map.put("CreatorRole", info.getRole());
        	map.put("UpdaterRole", info.getRole());
        }
        Object proceed = invocation.proceed();
        if(proceed != null&&(proceed instanceof Integer)){
        	int rows = (int) proceed;
        	if(rows<1){
        		throw new AppChkException(1004, "app.data.version.old");
        	}
        }
        return proceed;
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
}
