package com.want.interceptor;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MainProdAccessInterceptor extends AbstractPhaseInterceptor<Message>{
	//获取Log日志类
	private final Log logger = LogFactory.getLog(MainProdAccessInterceptor.class);
	
	
    private static String accessIp; 
	  
	//创建默认的构造方法  
	public MainProdAccessInterceptor(){
		super(Phase.RECEIVE);
	}
	
	@SuppressWarnings("static-access")
	@Value("${webservices.interceptor.accessIp}")
	public void setAccessIp(String accessIp) {
		this.accessIp = accessIp;
	}

	@Override
	public void handleMessage(Message msg) throws Fault {
		//得到允许访问的IP地址列表 ,从配置文件中读取
		logger.info("IP address " + accessIp );
		//获取request。
		HttpServletRequest request = (HttpServletRequest) msg.get(AbstractHTTPDestination.HTTP_REQUEST);
		//获取当前访问者的IP地址
		String ipAddress = this.getIp(request);
		if(StringUtils.isNotBlank(accessIp)){
			String[] ips = accessIp.split(",");
			List<String> iplist = Arrays.asList(ips);
			if(iplist.contains(ipAddress)){
				logger.info("IP address " + ipAddress + " is allowed");
			}else{
				logger.warn("IP address " + ipAddress + " is not allowed");
				//如果IP地址不被允许，则抛出异常信息给CXF处理。
				//throw new Fault(new IllegalAccessException("IP address " + ipAddress + " is not allowed"));
			}
		}
	}
	//得到访问者的IP地址。
	private String getIp(HttpServletRequest request){
		String ip = request.getHeader("x-forwarded-for");
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
			logger.info("getIp Proxy-Client-IP:"+ip);
		}
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
			logger.info("WL-Proxy-Client-IP:"+ip);
		}
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
			logger.info("getIp Proxy-Client-IP:"+ip);
		}
		return ip;
	}

}
