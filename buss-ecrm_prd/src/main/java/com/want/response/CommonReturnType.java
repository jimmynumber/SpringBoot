package com.want.response;

import java.io.Serializable;

public class CommonReturnType implements Serializable {
	private static final long serialVersionUID = -5394621780550844975L;
	private String status; //  success:"成功",fail,"失败"
	private Object data;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static CommonReturnType create(Object result) {
		return CommonReturnType.create(result,"success");
	}
	public static CommonReturnType create(Object result,String status) {
		CommonReturnType crt= new CommonReturnType();
		crt.setStatus(status);
		crt.setData(result);
		return crt;
	}
	
}
