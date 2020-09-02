package com.want.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OT_RETURN implements Serializable{

    private String ORDER_CODE;
    private String TYPE;
    private String MESSAGE;
    
	public String getORDER_CODE() {
		return ORDER_CODE;
	}
	public void setORDER_CODE(String oRDER_CODE) {
		ORDER_CODE = oRDER_CODE;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	

}