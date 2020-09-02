package com.want.po;

import java.util.Date;

public class OrderReset{
    
    private String erpOrderCode;
    private String orderCode;
    private String reaetReason;
    private String createUser;
    private Date createDate;
    
	public String getErpOrderCode() {
		return erpOrderCode;
	}
	public void setErpOrderCode(String erpOrderCode) {
		this.erpOrderCode = erpOrderCode;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getReaetReason() {
		return reaetReason;
	}
	public void setReaetReason(String reaetReason) {
		this.reaetReason = reaetReason;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	
}