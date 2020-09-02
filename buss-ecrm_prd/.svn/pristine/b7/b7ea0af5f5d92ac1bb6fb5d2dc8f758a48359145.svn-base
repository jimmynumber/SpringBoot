package com.want.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.error.BusinessException;

@CrossOrigin
@RestController 
public class TestController extends BaseController {

	@GetMapping("/getInfo") 
	public String  getInfo() throws BusinessException { 
		if(true) {
			//int a=7/0;
			//throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"什么情况");	
		}
		return "hello world"; 
	} 
	
	
	
	
}
