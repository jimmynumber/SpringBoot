package com.want.wantworld.vo;

import java.io.Serializable;

public class LoginVo implements Serializable {
	private static final long serialVersionUID = 218387398141574862L;
	private String userId;      //       
	private String password;    //         
	private String deviceid;    //  设备Id       
	private String osType;      //  设备类型     
	private String baseBand;    //  部门  
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getBaseBand() {
		return baseBand;
	}
	public void setBaseBand(String baseBand) {
		this.baseBand = baseBand;
	}
	@Override
	public String toString() {
		return "LoginVo [userId=" + userId + ", password=" + password + ", deviceid=" + deviceid + ", osType=" + osType
				+ ", baseBand=" + baseBand + "]";
	}
}
