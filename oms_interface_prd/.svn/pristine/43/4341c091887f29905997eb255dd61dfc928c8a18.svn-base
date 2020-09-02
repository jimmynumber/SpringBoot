//package com.want.config.datesource;
//
//import java.util.HashMap;
//
//import javax.sql.DataSource;
//
//import org.apache.catalina.Context;
//import org.apache.catalina.startup.Tomcat;
//import org.apache.tomcat.util.descriptor.web.ContextResource;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
//import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//@Configuration
//public class JNDIConfig {
//	
//		
//	
//	@Bean(name = "webServerFactory187111")
//	//@Scope("prototype")
//	@Primary
//    public ServletWebServerFactory webServerFactory187() {
//		return new TomcatServletWebServerFactory(){
//            @Override
//            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
//                // 启用默认禁用的JNDI命名
//                tomcat.enableNaming();
//                return super.getTomcatWebServer(tomcat);
//            }
//            @Override
//            protected void postProcessContext(Context context) {
//            	ContextResource resource = new ContextResource();
//            	resource.setName("jdbc/oracle187shqwtxywdb");
//            	resource.setType(DataSource.class.getName());
//            	resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
//            	resource.setProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
//            	resource.setProperty("url", "jdbc:oracle:thin:@10.0.26.225:1521/ORCLPDB");
//            	resource.setProperty("username", "salesgroup2_rc");
//            	resource.setProperty("password","salesgroup2_rc#TEST");
//            	context.getNamingResources().addResource(resource);
//            	super.postProcessContext(context);
//            }
//        };
//    }
//	/*  
//	@Bean(name = "webServerFactory1882222")
//    public ServletWebServerFactory webServerFactory188() {
//		return  new TomcatServletWebServerFactory(){
//            @Override
//            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
//                // 启用默认禁用的JNDI命名
//                tomcat.enableNaming();
//                return super.getTomcatWebServer(tomcat);
//            }
//            @Override
//            protected void postProcessContext(Context context) {
//                ContextResource resource = new ContextResource();
//                resource.setName("jdbc/oracle225ORCLPDB/salesgroup2_ods");
//                resource.setType(DataSource.class.getName());
//                resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
//                resource.setProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
//                resource.setProperty("url", "jdbc:oracle:thin:@10.0.26.225:1521/ORCLPDB");
//                resource.setProperty("username", "SALESGROUP2_ODS");
//                resource.setProperty("password","salesgroup2_ods#TEST");
//                context.getNamingResources().addResource(resource);
//                super.postProcessContext(context);
//            }
//        };
//    }
//	 */
//}
