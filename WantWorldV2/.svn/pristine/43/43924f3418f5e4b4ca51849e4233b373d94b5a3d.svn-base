package com.want.wantworld.interceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.want.wantworld.entity.LoginToken;
import com.want.wantworld.service.ILoginService;

@Component
public class TokenFilter extends OncePerRequestFilter{
	
	private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);
	
	@Autowired
	private ILoginService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		logger.info("TokenFilter.doFilterInternal =====>> path="+request.getServletPath());
		//其余方法需要token验证
    	String token = request.getHeader("token");
    	LoginToken loginToken = null;
    	if(!StringUtils.isEmpty(token)) {
    		loginToken = service.getLoginByToken(token);
    	}
    	if(loginToken != null) {
    		List<GrantedAuthority> authorities = new ArrayList<>();
    		
    		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginToken,
					null, authorities);
			SecurityContextHolder.getContext().setAuthentication(authentication);
    	}
    	
//    	if (!bool) {//验证失败
//    		response.setCharacterEncoding("UTF-8");  
//    		response.setContentType("application/json; charset=utf-8");
//    		PrintWriter out = null ;
//    		try{
//    		    JSONObject res = new JSONObject();
//    		    res.put("code", "401");
//    		    res.put("message", "登录超时，请重新登录！");
//    		    out = response.getWriter();
//    		    out.append(res.toString());
//    		}
//    		catch (Exception e){
//    		    e.printStackTrace();
//    		    response.sendError(500);
//    		}
//    	}
        filterChain.doFilter(request,response);
		
	}

}
