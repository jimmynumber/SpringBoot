package com.want.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CustomerReceverAddresses  implements Serializable{
	
	private String sapReceiverCode;
	private String sapReceiverName;
	private String address;
	public String getSapReceiverCode() {
		return sapReceiverCode;
	}
	public void setSapReceiverCode(String sapReceiverCode) {
		this.sapReceiverCode = sapReceiverCode;
	}
	public String getSapReceiverName() {
		return sapReceiverName;
	}
	public void setSapReceiverName(String sapReceiverName) {
		this.sapReceiverName = sapReceiverName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
