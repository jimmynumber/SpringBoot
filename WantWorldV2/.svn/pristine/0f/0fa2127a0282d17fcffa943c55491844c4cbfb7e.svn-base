package com.want.wantworld.vo;

import java.io.Serializable;
import java.util.List;

public class CommonReturnType implements Serializable {
	private static final long serialVersionUID = -5394621780550844975L;
	private String status; //  success:"成功",fail,"失败"
	private Object data;
	private List<?> list;
	private String msg;
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
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
	public static CommonReturnType create(Object result,List<?> list,String msg) {
		return CommonReturnType.create(result,list,msg,"success");
	}
	public static CommonReturnType create(Object result,List<?> list,String msg,String status) {
		CommonReturnType crt= new CommonReturnType();
		crt.setStatus(status);
		crt.setData(result);
		crt.setList(list);
		crt.setMsg(msg);
		return crt;
	}
	
}
