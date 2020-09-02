package com.want.vo;

import java.io.Serializable;
import java.util.List;

public class ErpOrder implements Serializable {
	private static final long serialVersionUID = -5346719398146146248L;
	private List<SubErpOrder> erpOrders; // 多个erp_order
    public List<SubErpOrder> getErpOrders() {
		return erpOrders;
	}
	public void setErpOrders(List<SubErpOrder> erpOrders) {
		this.erpOrders = erpOrders;
	}
	public static  class SubErpOrder{
		protected String sid;         // erp_order 主键sid
		protected String empId;       // 操作人员工号
		protected String empName;     // 操作人员姓名
		protected String desciption;  // 关闭原因
		public String getSid() {
			return sid;
		}
		public void setSid(String sid) {
			this.sid = sid;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getDesciption() {
			return desciption;
		}
		public void setDesciption(String desciption) {
			this.desciption = desciption;
		}
		@Override
		public String toString() {
			return "ErpOrder [sid=" + sid + ", empId=" + empId + ", empName=" + empName + ", desciption=" + desciption
					+ "]";
		}
	}
}
