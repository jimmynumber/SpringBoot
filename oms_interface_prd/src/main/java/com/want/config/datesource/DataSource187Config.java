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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
@MapperScan(basePackages = DataSource187Config.PACKAGE, sqlSessionTemplateRef = "SqlSessionTemplate187")
public class DataSource187Config {
	protected static final String PACKAGE = "com.want.mapper187";
	static final String MAPPER_LOCATION = "classpath:/common/mybatis/mapper187/*.xml";

	@Value("${spring.datasource187.jndi-name}")
	private String jndiName;
	
	@Bean(name = "dataSource187")
	@Primary
	public DataSource dataSource() {
	    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
	    lookup.setResourceRef(true);
	    return lookup.getDataSource(jndiName);
	}
	
//	@Bean(name = "dataSource187")
//	@ConfigurationProperties("spring.datasource187")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}

	@Bean(name = "transactionManager187")
	@Primary
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource187") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionFactory187")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource187") DataSource dataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		sessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver().getResources(DataSource187Config.MAPPER_LOCATION));
		return sessionFactory.getObject();
	}

	@Bean(name = "SqlSessionTemplate187")
	@Primary
	public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory187") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
