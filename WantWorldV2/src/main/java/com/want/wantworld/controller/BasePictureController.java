package com.want.wantworld.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.want.wantworld.service.IBasePictureService;
import com.want.wantworld.utils.WResultUtil;
import com.want.wantworld.vo.CommonReturnType;
import com.want.wantworld.vo.WResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "图片")
@RestController
@RequestMapping("/picture")
public class BasePictureController {

	private static final Logger logger = LoggerFactory.getLogger(BasePictureController.class);

	@Autowired
	private IBasePictureService service;

	@ApiOperation(value = "获取图片信息", nickname="picture", notes = "")
	@PostMapping("/getBasePicture")
	public WResult<?> getBasePicture() {
		logger.info("BasePictureController.getBasePicture");
		return WResultUtil.ok(service.getAllPicture());
	}
}