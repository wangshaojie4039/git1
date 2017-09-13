package cn.imexue.ec.common.configuration.dataSource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@MapperScan(basePackages = "cn.imexue.ec.common.mapper.eccard")
public class DataSourceEccardConfig {

	private ResourceLoader loader = new DefaultResourceLoader();

	@Value("${mybatis.config:classpath:/mybatis-config.xml}")
	private String mybatisConfig;
	
	@Value("${mybatis.eccard.mapper-locations}")
	private String mapperLocations;
	
	@Value("${mybatis.type-aliases-package}")
	private String typeAliasesPackage;
	
    @Bean("eccardSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("eccardDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        bean.setTypeAliasesPackage(typeAliasesPackage);
        bean.setConfigLocation(loader.getResource(mybatisConfig));
        return bean.getObject();
    }

    @Bean("eccardTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("eccardDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean("eccardSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("eccardSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
