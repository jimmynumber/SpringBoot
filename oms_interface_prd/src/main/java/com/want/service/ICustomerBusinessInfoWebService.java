package com.want.service;

import java.util.List;

import com.want.response.ResponseEntity;
import com.want.vo.CustomerBusinessInfo;

public interface ICustomerBusinessInfoWebService {
	ResponseEntity syncCustomerBusinessInfo(List<CustomerBusinessInfo> customerBusinessInfoList) ;
}
