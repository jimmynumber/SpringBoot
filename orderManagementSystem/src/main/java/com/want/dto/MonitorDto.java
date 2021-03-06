package com.want.dto;

import java.io.Serializable;
import java.util.Date;

/**
* 记录状态信息
* @author 00320558 
* @date   2019年11月01日
*/
public class MonitorDto implements Serializable{

	private static final long serialVersionUID = -8157803205560220482L;
	private String sid;              //主键sid
	private String funcid;           //功能id
	private Date startDate;          //开始时间
	private Date endDate;            //结束时间
	private String status;           //状态 0:失败,1:成功,2:执行中
	private String reason;           //异常原因
	private String issend;           //是否发邮件 0:未发送,1:发送
	private String eid;              //异常ID
	private String type;             //标识 S:全部成功,E:全部失败或者失败
	private String message;          //返回msg
	private String createUser;       //创建人
	private Date createDate;         //创建时间
	private String updateUser;       //修改人
	private Date updateDate;         //修改时
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getFuncid() {
		return funcid;
	}
	public void setFuncid(String funcid) {
		this.funcid = funcid;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getIssend() {
		return issend;
	}
	public void setIssend(String issend) {
		this.issend = issend;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
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
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "MonitorDto [sid=" + sid + ", funcid=" + funcid + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", reason=" + reason + ", issend=" + issend + ", eid=" + eid + ", type=" + type
				+ ", message=" + message + ", createUser=" + createUser + ", createDate=" + createDate + ", updateUser="
				+ updateUser + ", updateDate=" + updateDate + "]";
	}
}


