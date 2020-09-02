package com.want.wantworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.want.wantworld.entity.FunctionInfo;
import com.want.wantworld.service.FunctionInfoService;
import com.want.wantworld.vo.WResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "菜单信息")
@RestController
@RequestMapping("/menu")
public class FunctionInfoController {
	
	private static final String TOKEN_KEY = "token";
	
	private static final Logger logger = LoggerFactory.getLogger(FunctionInfoController.class);
	
	@Autowired
	private FunctionInfoService functionInfoService;
	
	/**
	 * 
	 * @Title: getMenuList
	 * @Description: 根据token获取用户菜单
	 * @param @param request
	 * @param @return 参数
	 * @return CommonReturnType 返回类型
	 * @throws
	 */
	@ApiOperation(value = "菜单列表", nickname="menus", notes = "")
	@PostMapping("/list")
	public WResult getMenuList(HttpServletRequest request){
		String token = request.getHeader(TOKEN_KEY);
		return functionInfoService.getMenuGroupByEmpId(token);
	}

}
