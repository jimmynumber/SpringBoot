package com.want.config.datesource;

//import javax.sql.DataSource;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

//@Configuration
//@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "sqlSessionFactory")
//public class DataSourceConfig {
//
//static final String PACKAGE = "com.want.mapper";
//static final String MAPPER_LOCATION = "classpath:/common/mybatis/mapper/*.xml";
//
////@Value("${db.datasource.jndiName}")
////private String jndiName;
////
////@Primary
////@Bean(name = "dataSource")
////public DataSource dataSource() {
////    JndiDataSourceLookup lookup = new JndiDataSourceLookup();
////    lookup.setResourceRef(true);
////    return lookup.getDataSource(jndiName);
////}
//
//@Value("${datasource.url}")
//private String url;
//
//@Value("${datasource.username}")
//private String user;
//
//@Value("${datasource.password}")
//private String password;
//
//@Value("${datasource.driver-class-name}")
//private String driverClass;
//
//@Bean(name = "dataSource")
//public DataSource dataSource() {
//    DruidDataSource dataSource = new DruidDataSource();
//    dataSource.setDriverClassName(driverClass);
//    dataSource.setUrl(url);
//    dataSource.setUsername(user);
//    dataSource.setPassword(password);
//    return dataSource;
//}
//
// @Bean(name = "transactionManager")
// @Primary
// public DataSourceTransactionManager transactionManager() {
//     return new DataSourceTransactionManager(dataSource());
// }
//
// @Bean(name = "sqlSessionFactory")
// @Primary
// public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)
//         throws Exception {
//     final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//     sessionFactory.setDataSource(dataSource);
//     sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//             .getResources(DataSourceConfig.MAPPER_LOCATION));
//     return sessionFactory.getObject();
// }
//}
