package com.want.service;

import java.util.List;
import java.util.Map;
import com.want.dto.FunctionInfo;

public interface ILoginService {
	
	//验证人员是否有权限登录
	public boolean validateLogin(String empId);
	
	//账密验证
	public Map<String, String> login(String empId, String password);
	
	//登入token信息变更
	public Map<String, String> loginToken(String empId, Map<String, String> map);

	//登出token信息变更
	public Map<String, String> logoutToken(String empId);
	
	//检查token信息
	public boolean checkToken(String empId, String token);
	
	//通过人员工号菜单信息
	public List<FunctionInfo> functionList(String empId);
	
}
