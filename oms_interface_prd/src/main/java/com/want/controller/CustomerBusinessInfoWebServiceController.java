package com.want.controller;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.want.response.ResponseEntity;
import com.want.service.ICustomerBusinessInfoWebService;
import com.want.vo.CustomerBusinessInfo;

@WebService
public class CustomerBusinessInfoWebServiceController {

	@Autowired
    private ICustomerBusinessInfoWebService Service;
	
	@PostMapping("/syncCustomerBusinessInfo")
	public ResponseEntity syncCustomerBusinessInfo(List<CustomerBusinessInfo> customerBusinessInfoList) {
		return Service.syncCustomerBusinessInfo(customerBusinessInfoList);
	}
}
