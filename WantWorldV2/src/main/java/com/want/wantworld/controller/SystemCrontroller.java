package com.want.wantworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.wantworld.service.BaseSystemLinkService;
import com.want.wantworld.vo.WResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "系统路径")
@CrossOrigin
@RestController 
public class SystemCrontroller {
	
	@Autowired
	private BaseSystemLinkService baseSystemLinkService;

	@ApiOperation(value = "配置链接", nickname="links", notes = "")
	@PostMapping("/systemLink")
	public WResult<?> getSystemLink() {
		return baseSystemLinkService.getSystemLinkList();
	}
}