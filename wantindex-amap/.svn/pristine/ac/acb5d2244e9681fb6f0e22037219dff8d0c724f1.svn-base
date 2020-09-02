package com.want.amap;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = {"com.want.amap.mapper.oracle"}, sqlSessionFactoryRef = "oracleSessionFactory")
public class OracleDataSourceConfig {
	@Bean(name = "oracleDataSource")
	@Qualifier("oracleDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.oracle")
	public DataSource oracleDataSource() {
		return (DataSource) DataSourceBuilder.create().build();
	}
	
	@Bean(name = "oracleSessionFactory")
    public SqlSessionFactory oracleSessionFactory(@Qualifier("oracleDataSource")DataSource oracleDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(oracleDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

}
