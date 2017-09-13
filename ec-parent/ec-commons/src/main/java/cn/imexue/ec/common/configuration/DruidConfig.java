package cn.imexue.ec.common.configuration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;

@Configuration
public class DruidConfig {

	private Logger	logger	= LoggerFactory.getLogger(getClass());

	@Value("${spring.datasource.url}")
	private String	dbUrl;

	@Value("${spring.datasource.username}")
	private String	username;

	@Value("${spring.datasource.password}")
	private String	password;

	@Value("${spring.datasource.driverclassname:com.mysql.jdbc.Driver:com.alibaba.druid.pool.DruidDataSource}")
	private String	driverClassName;

	@Value("${spring.datasource.initialSize:5}")
	private int		initialSize;

	@Value("${spring.datasource.minIdle:5}")
	private int		minIdle;

	@Value("${spring.datasource.maxActive:20}")
	private int		maxActive;

	@Value("${spring.datasource.maxWait:60000}")
	private int		maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis:60000}")
	private int		timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis:30000}")
	private int		minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery:SELECT 1}")
	private String	validationQuery;

	@Value("${spring.datasource.testWhileIdle:true}")
	private boolean	testWhileIdle;

	@Value("${spring.datasource.testOnBorrow:false}")
	private boolean	testOnBorrow;

	@Value("${spring.datasource.testOnReturn:false}")
	private boolean	testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements:true}")
	private boolean	poolPreparedStatements;

	@Value("${spring.datasource.filters:stat,wall,slf4j}")
	private String	filters;

	@Value("${spring.datasource.maxOpenPreparedStatements:20}")
	private Integer	maxOpenPreparedStatements;

	@Value("${spring.datasource.connectionProperties:druid.stat.mergeSql=true;druid.stat.slowSqlMillis=5000}")
	private String	connectionProperties;

	@Value("${spring.ec.card.datasource.url}")
	private String	cardDBUrl;

	@Value("${spring.ec.card.datasource.username}")
	private String	cardDBUsername;

	@Value("${spring.ec.card.datasource.password}")
	private String	cardDBPassword;

	@Value("${druid.sql.log.select:false}")
	private boolean	selectLog;

	@Bean
	public FilterRegistrationBean filterRegistrationBean() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		WebStatFilter filter = new WebStatFilter();
		registrationBean.addInitParameter("exclusions", "/static/*,/druid/*");
		registrationBean.addInitParameter("sessionStatMaxCount", "1000");
		registrationBean.addInitParameter("profileEnable", "true");
		// registrationBean.addInitParameter("principalSessionName", FWConstants.SLOGIN);
		// registrationBean.addInitParameter("sessionStatEnable", "true");
		registrationBean.setFilter(filter);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {

		ServletRegistrationBean registrationBean = new ServletRegistrationBean();

		registrationBean.addUrlMappings("/druid/*");
		registrationBean.setServlet(new StatViewServlet());
		return registrationBean;
	}

	@Bean(name = "eccardDataSource")
	public DataSource ecCardDataSource() {

		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(cardDBUrl);
		datasource.setUsername(cardDBUsername);
		datasource.setPassword(cardDBPassword);
		setProperties(datasource);
		Log.debug("创建card数据源成功");
		return datasource;
	}

	@Bean
	@Primary
	public DataSource druidDataSource() {

		DruidDataSource datasource = new DruidDataSource();

		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		setProperties(datasource);
		Log.debug("创建数据源成功");
		return datasource;
	}

	private void setProperties(DruidDataSource datasource) {

		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource
				.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
		datasource.setConnectionProperties(connectionProperties);

		List<Filter> filterList = new ArrayList<Filter>();
		WallFilter wallFilter = new WallFilter();
		WallConfig wallConfig = new WallConfig();
		wallConfig.setMultiStatementAllow(true);
		wallFilter.setConfig(wallConfig);
		filterList.add(wallFilter);
		datasource.setProxyFilters(filterList);
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter error",
					e.getMessage());
		}
	}

}
