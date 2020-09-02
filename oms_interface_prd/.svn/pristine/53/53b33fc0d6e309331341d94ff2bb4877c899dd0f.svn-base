package com.want.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.want.error.BusinessException;
import com.want.error.EmBusinessError;
import com.want.response.CommonReturnType;


public class BaseController {
    //定义 exceptionHandler 解决未被 controller 层处理的exception
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object handlerException(HttpServletRequest request,Exception ex) {
		Map<String,Object> responseMap = new HashMap<>();
		if(ex instanceof BusinessException) {
			BusinessException be=(BusinessException)ex;
			responseMap.put("errCode", be.getErrCode());
			responseMap.put("errMsg", be.getErrMsg());
		}else {
			responseMap.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
			responseMap.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
		}
		return CommonReturnType.create(responseMap, "fail");
	}
}
