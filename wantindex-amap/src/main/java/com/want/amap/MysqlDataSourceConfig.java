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
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = {"com.want.amap.mapper.mysql"}, sqlSessionFactoryRef = "mysqlSessionFactory")
public class MysqlDataSourceConfig {
	@Bean(name = "mysqlDataSource")
	@Qualifier("mysqlDataSource")
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource mysqlDataSource() {
		return (DataSource) DataSourceBuilder.create().build();
	}
	
	@Bean(name = "mysqlSessionFactory")
	@Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("mysqlDataSource")DataSource mysqlDataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(mysqlDataSource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(true);
        sessionFactory.setConfiguration(configuration);
        return sessionFactory.getObject();
    }

}
