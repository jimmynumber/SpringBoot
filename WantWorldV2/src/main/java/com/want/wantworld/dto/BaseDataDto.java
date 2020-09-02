package com.want.wantworld.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "设备版本信息", description = "一律放在 ResponseDataEntity 的 data 屬性，不會單獨回傳")
public class BaseDataDto {
	
	@ApiModelProperty(value = "token信息")
	private String token;
	
	@ApiModelProperty(value = "版本号")
	@NotBlank(message="版本号不能为空")
	private String version;
	
	@ApiModelProperty(value = "系统类型")
	@NotBlank(message="系统类型不能为空")
	private String osType;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

}
