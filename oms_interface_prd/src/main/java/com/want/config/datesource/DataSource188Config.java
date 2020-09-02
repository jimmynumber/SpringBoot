package com.want.config.datesource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@MapperScan(basePackages = DataSource188Config.PACKAGE, sqlSessionTemplateRef = "SqlSessionTemplate188")
public class DataSource188Config {
	protected static final String PACKAGE = "com.want.mapper188";
	static final String MAPPER_LOCATION = "classpath:/common/mybatis/mapper188/*.xml";

	@Value("${spring.datasource188.jndi-name}")
	private String jndiName;
	
	@Bean(name = "dataSource188")
	public DataSource dataSource() {
	    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	    lookup.setResourceRef(true);
	    return lookup.getDataSource(jndiName);
	}
	
	/*********************************/
	
//	@Bean(name = "dataSource188")
//	@ConfigurationProperties("spring.datasource188")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	@Bean(name = "transactionManager188")
	//@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource188") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionFactory188")
	//@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource188") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(DataSource188Config.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}
	
	@Bean(name = "SqlSessionTemplate188")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory188") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
