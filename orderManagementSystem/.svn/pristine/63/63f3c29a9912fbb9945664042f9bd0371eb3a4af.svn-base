package com.want.config.webservice;

import javax.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.want.controller.AttenceWebServiceController;
import com.want.controller.OrderWebServiceController;
import com.want.controller.StoreVisitWebServiceController;
import com.want.controller.StoreVisitTargetWebServiceController;

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
    public OrderWebServiceController orderService()
    {
        return  new OrderWebServiceController();
    }
   
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), orderService());
        endpoint.publish("/syncOrder"); 
        return endpoint;
    }
    
    @Bean
    public AttenceWebServiceController attenceService()
    {
        return  new AttenceWebServiceController();
    }
    
    @Bean
    public Endpoint endpoint_1() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), attenceService());
        endpoint.publish("/syncAttence"); 
        return endpoint;
    }
    
    @Bean
    public StoreVisitTargetWebServiceController storeVisitTargetService()
    {
        return  new StoreVisitTargetWebServiceController();
    }
    
    @Bean
    public Endpoint visitTrackingEndpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), storeVisitTargetService());
        endpoint.publish("/syncStoreVisitTarget"); 
        return endpoint;
    }
    
    @Bean
    public StoreVisitWebServiceController storeVisitService()
    {
        return  new StoreVisitWebServiceController();
    }
    
    @Bean
    public Endpoint visitTrackingDetailEndpoint() {
        EndpointImpl endpoint=new EndpointImpl(springBus(), storeVisitService());
        endpoint.publish("/syncStoreVisit"); 
        return endpoint;
    }
    
    
}