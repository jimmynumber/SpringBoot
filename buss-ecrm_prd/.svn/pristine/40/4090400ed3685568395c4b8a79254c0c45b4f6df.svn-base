package com.want.config.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.want.controller.MainProdWebServiceController;
import com.want.interceptor.MainProdAccessInterceptor;
import com.want.interceptor.MainProdAuthInterceptor;
import com.want.service.CommonService;

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
    public MainProdWebServiceController mainProdService()
    {
    	return  new MainProdWebServiceController();
    }
    
    @Bean
    public Endpoint endpoint_2() {
    	EndpointImpl endpoint=new EndpointImpl(springBus(), mainProdService());
    	endpoint.publish("/getMainProd"); 
    	//通过拦截器校验用IP
    	endpoint.getInInterceptors().add(new MainProdAccessInterceptor());
    	//通过拦截器校验用户名与密码
    	endpoint.getInInterceptors().add(new MainProdAuthInterceptor());
    	return endpoint;
    }
    
    @Autowired
	private CommonService commonService;
 
	/** JAX-WS **/
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), commonService);
		endpoint.publish("/CommonService");
		return endpoint;
	}

}