package com.want.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.want.dto.FunctionInfo;
import com.want.po.LoginToken;

@Mapper
public interface LoginMapper { 
	
	//验证人员是否有权限登录
	public String validateLogin(@Param("empId")String empId);
	
	//通过人员工号获取token信息
	public LoginToken getTokenByEmpId(@Param("empId")String empId);
	
	//更新token信息
	public void updateLoginToken(@Param("loginToken")LoginToken loginToken);
	
	//新建token信息
	public void saveLoginToken(@Param("loginToken")LoginToken loginToken);

	//通过人员工号和token获取token信息
	public LoginToken getTokenByEmpIdAndToken(@Param("empId")String empId, @Param("token")String token);
	
	//通过人员工号菜单信息
	public List<FunctionInfo> functionList(@Param("empId")String empId);
} 