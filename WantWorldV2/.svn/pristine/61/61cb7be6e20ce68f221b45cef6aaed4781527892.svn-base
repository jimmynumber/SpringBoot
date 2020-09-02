package com.want.wantworld.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.want.wantworld.controller.ValidateTokenWebServiceController;
import com.want.wantworld.interceptor.AuthInterceptor;
 

@Configuration
public class WebServiceConfig {

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public ServletRegistrationBean disServlet(){
        return new ServletRegistrationBean(new CXFServlet(),"/WebService/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus()
    {
        return  new SpringBus();
    }

    @Bean
    public ValidateTokenWebServiceController validateToken()
    {
        return  new ValidateTokenWebServiceController();
    }
   
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), validateToken());
        //通过拦截器校验用户名与密码
    	endpoint.getInInterceptors().add(new AuthInterceptor());
        endpoint.publish("/validateToken"); 
        return endpoint;
    }
    
   
}