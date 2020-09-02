package com.want.wantworld.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.want.wantworld.annotation.PassToken;
import com.want.wantworld.service.ILoginService;

@Configuration
public class GlobalInterceptorConfig implements WebMvcConfigurer{
	
	@Autowired
    private ILoginService service;
    
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            	System.out.println("前置方法被执行");
            	
            	HandlerMethod handlerMethod = (HandlerMethod) handler; 
            	String methodName = handlerMethod.getMethod().getName();
            	System.out.println("前置方法被执行"+handlerMethod+"."+methodName);
            	//如果是访问登录方法则放行
            	if ("login".equals(methodName)) {
            		return true;
            	}
            Method method=handlerMethod.getMethod();
            	//检查是否有passtoken注释，有则跳过认证
            if (method.isAnnotationPresent(PassToken.class)) {
                PassToken passToken = method.getAnnotation(PassToken.class);
                if (passToken.required()) {
                    return true;
                }
            }
//            	if ("getBasePicture".equals(methodName)) {
//            		return true;
//            	}
            	//其余方法需要token验证
            	String empId = request.getHeader("userId");
            	String token = request.getHeader("token");
            	boolean bool =service.checkToken(empId, token);
            	if (!bool) {//验证失败
            		response.setCharacterEncoding("UTF-8");  
            		response.setContentType("application/json; charset=utf-8");
            		PrintWriter out = null ;
            		try{
            		    JSONObject res = new JSONObject();
            		    res.put("code", "401");
            		    res.put("message", "登录超时，请重新登录！");
            		    out = response.getWriter();
            		    out.append(res.toString());
            		}
            		catch (Exception e){
            		    e.printStackTrace();
            		    response.sendError(500);
            		}
            	}
            	return bool;
            }
 
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                
            }
 
            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            	
            }
        });
    }
}
