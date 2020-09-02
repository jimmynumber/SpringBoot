package com.want.wantworld.config.datasouce;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionTemplateRef = "SqlSessionTemplate")
public class DataSourceConfig {
	protected static final String PACKAGE = "com.want.wantworld.dao";
	static final String MAPPER_LOCATION = "classpath:/mybatis/mappers/*.xml";

	@Value("${spring.datasource.jndi-name}")
	private String jndiName;
	
	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
	    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	    lookup.setResourceRef(true);
	    return lookup.getDataSource(jndiName);
	}
	
//	@Bean(name = "dataSource")
//	@ConfigurationProperties("spring.datasource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	@Bean(name = "transactionManager")
	//@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(DataSourceConfig.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}

	@Bean(name = "SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
