package com.want.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.want.dto.EmpInfo;

@Mapper
public interface UserManagementMapper { 
	
	public List<EmpInfo> empQuery(@Param("orgId")String orgId,@Param("empId")String empId);
	
	public String orgQuery(@Param("orgId")String orgId);
	
} 