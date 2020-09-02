package com.want.wantworld.service;

import java.util.Map;

import com.want.wantworld.entity.LoginToken;
import com.want.wantworld.vo.LoginVo;

public interface ILoginService {
	
	//验证人员是否有权限登录
	public boolean validateLogin(String empId);
	
	//账密验证
	public Map<String, String> login(String empId, String password);
	
	//登入token信息变更
	public Map<String, String> loginToken(LoginVo loginVo, Map<String, String> map);

	//登出token信息变更
	public Map<String, String> logoutToken(String empId);
	
	//检查token信息
	public boolean checkToken(String empId, String token);
	
	public Map<String, String> validateToken(String token );
	
	public LoginToken getLoginByToken(String token);
	
	 	
}
