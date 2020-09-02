package com.want.wantworld.config.datasouce;
import javax.sql.DataSource;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.tomcat.util.descriptor.web.ContextResource;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JNDIConfig {
    
  @Bean(name = "webServerFactory")
  @Primary
    public ServletWebServerFactory webServerFactory187() {
      return new TomcatServletWebServerFactory(){
            @Override
            protected TomcatWebServer getTomcatWebServer(Tomcat tomcat) {
                // 启用默认禁用的JNDI命名
                tomcat.enableNaming();
                return super.getTomcatWebServer(tomcat);
            }
            @Override
            protected void postProcessContext(Context context) {
              ContextResource resource = new ContextResource();
              resource.setName("jdbc/oracle130SDB/wantworldv2");
              resource.setType(DataSource.class.getName());
              resource.setProperty("factory", "org.apache.tomcat.jdbc.pool.DataSourceFactory");
              resource.setProperty("driverClassName", "oracle.jdbc.driver.OracleDriver");
              resource.setProperty("url", "jdbc:oracle:thin:@10.0.1.130:1521:SDB");
              resource.setProperty("username", "wantworldv2");
              resource.setProperty("password","wantworldv2#DEV");
              context.getNamingResources().addResource(resource);
              super.postProcessContext(context);
            }
        };
    }
  }
