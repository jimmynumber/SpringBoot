package com.want.dto;

import java.io.Serializable;



public class ResponseEntity implements Serializable  {

	private static final long serialVersionUID = 8097405633559439961L;
	private String type;      //标识，成功还是失败
	private String message;   //具体信息
	private String eid;       //传送失败记录的ID
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	@Override
	public String toString() {
		return "ResponseEntity [type=" + type + ", message=" + message + ", eid=" + eid + "]";
	}
}
