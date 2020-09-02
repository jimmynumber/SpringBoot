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

import com.want.controller.CustomerBusinessInfoWebServiceController;
import com.want.controller.ProdStructWebServiceController;
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
    
    @Autowired
	private CommonService commonService;
 
	/** JAX-WS **/
	@Bean
	public Endpoint endpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), commonService);
		endpoint.publish("/CommonService");
		return endpoint;
	}
	@Bean
    public ProdStructWebServiceController prodStructService()
    {
        return  new ProdStructWebServiceController();
    }
   
    @Bean
    public Endpoint prodStructEndpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), prodStructService());
        endpoint.publish("/syncProdStruct"); 
        return endpoint;
    }
    @Bean
    public CustomerBusinessInfoWebServiceController customerBusinessInfoService()
    {
        return  new CustomerBusinessInfoWebServiceController();
    }
   
    @Bean
    public Endpoint customerBusinessInfoServiceEndpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), customerBusinessInfoService());
        endpoint.publish("/syncCustomerBusinessInfo"); 
        return endpoint;
    }
}