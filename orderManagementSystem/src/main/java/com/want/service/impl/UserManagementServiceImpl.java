package com.want.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.want.dto.EmpInfo;
import com.want.mapper.UserManagementMapper;
import com.want.service.IUserManagementService;

@Service 
public class UserManagementServiceImpl implements IUserManagementService{
	
	@Autowired
    private UserManagementMapper userManagementMapper;
	
	public List<EmpInfo> empQuery(String orgId,String empId) {
		List<EmpInfo> empInfoList = userManagementMapper.empQuery(orgId,empId);
		if(empInfoList != null && empInfoList.size() > 0) {
			for(EmpInfo empInfo : empInfoList) {
				empInfo.setOrgName(userManagementMapper.orgQuery(empInfo.getOrgId()));
			}
		}
		return empInfoList;
	}
	
	
}