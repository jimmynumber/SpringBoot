package com.want.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.want.dto.EmpInfo;
import com.want.service.IUserManagementService;

@CrossOrigin
@RestController 
public class UserManagementController {

	@Autowired
    private IUserManagementService service;
	
	@GetMapping("/empQuery") 
	public List<EmpInfo> empQuery(String orgId,String empId) { 
		return service.empQuery(orgId,empId); 
	} 
	
}
