package com.want.service;

import java.util.List;
import com.want.dto.EmpInfo;

public interface IUserManagementService {

	public List<EmpInfo> empQuery(String orgId,String empId);
	
}
