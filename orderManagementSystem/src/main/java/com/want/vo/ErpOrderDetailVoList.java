package com.want.vo;

import java.io.Serializable;
import java.util.List;

public class ErpOrderDetailVoList implements Serializable{
	private static final long serialVersionUID = 3488845147601037650L;
	
	private List<SubErpOrderList> erpOrders;
	
	public List<SubErpOrderList> getErpOrders() {
		return erpOrders;
	}

	public void setErpOrders(List<SubErpOrderList> erpOrders) {
		this.erpOrders = erpOrders;
	}
	
	public static class SubErpOrderList{
		private String erpOrderSid;     // erp_order 主键sid
		private String empId;           // 操作人员工号
		private String empName;         // 操作人员姓名
		private String orderRebateAmount;         // 订单返利金额
		private String customerCode;         // 客户编码
		private String openOrderDate;         // 开单时间
		private List<SubErpOrderDetailList> erpOrderDetails; // 一对多个 ErpOrderDetail
		
		public String getCustomerCode() {
			return customerCode;
		}
		public void setCustomerCode(String customerCode) {
			this.customerCode = customerCode;
		}
		
		public String getOpenOrderDate() {
			return openOrderDate;
		}
		public void setOpenOrderDate(String openOrderDate) {
			this.openOrderDate = openOrderDate;
		}
		
		public String getErpOrderSid() {
			return erpOrderSid;
		}
		public void setErpOrderSid(String erpOrderSid) {
			this.erpOrderSid = erpOrderSid;
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
		
		public String getOrderRebateAmount() {
			return orderRebateAmount;
		}
		public void setOrderRebateAmount(String orderRebateAmount) {
			this.orderRebateAmount = orderRebateAmount;
		}
		public List<SubErpOrderDetailList> getErpOrderDetails() {
			return erpOrderDetails;
		}
	
		public void setErpOrderDetails(List<SubErpOrderDetailList> erpOrderDetails) {
			this.erpOrderDetails = erpOrderDetails;
		}
	
		@Override
		public String toString() {
			return "ErpOrderDetailVo [erpOrderSid=" + erpOrderSid + ", empId=" + empId + ", empName=" + empName
					+ ", erpOrderDetails=" + erpOrderDetails + "]";
		}
	
		public static class SubErpOrderDetailList{
			protected String sid;               //sid
			protected String erpOrderSid;       //主表id
			protected String productId;         //sku
			protected String productName;       //sku名称
			protected String unit;              //单位
			protected String count;             //数量
			protected String salePrice;         //开单价
			protected String discountPrice;     //折后单价
			protected String rebateDiscountPrice;     //折后单价
			protected String sumAmount;         //金额
			protected String createUser;        //创建者
			protected String createDate;        //创建时间
			protected String erpOrderLine;      //erp行项目编码
			protected String desciption;        //描述
			protected String unitName;          //单位名称
			protected String itemMemo;          //行备注
			public String getSid() {
				return sid;
			}
			public void setSid(String sid) {
				this.sid = sid;
			}
			public String getErpOrderSid() {
				return erpOrderSid;
			}
			public void setErpOrderSid(String erpOrderSid) {
				this.erpOrderSid = erpOrderSid;
			}
			public String getProductId() {
				return productId;
			}
			public void setProductId(String productId) {
				this.productId = productId;
			}
			public String getProductName() {
				return productName;
			}
			public void setProductName(String productName) {
				this.productName = productName;
			}
			public String getUnit() {
				return unit;
			}
			public void setUnit(String unit) {
				this.unit = unit;
			}
			public String getCount() {
				return count;
			}
			public void setCount(String count) {
				this.count = count;
			}
			public String getSalePrice() {
				return salePrice;
			}
			public void setSalePrice(String salePrice) {
				this.salePrice = salePrice;
			}
			public String getDiscountPrice() {
				return discountPrice;
			}
			public void setDiscountPrice(String discountPrice) {
				this.discountPrice = discountPrice;
			}
			public String getRebateDiscountPrice() {
				return rebateDiscountPrice;
			}
			public void setRebateDiscountPrice(String rebateDiscountPrice) {
				this.rebateDiscountPrice = rebateDiscountPrice;
			}
			public String getSumAmount() {
				return sumAmount;
			}
			public void setSumAmount(String sumAmount) {
				this.sumAmount = sumAmount;
			}
			public String getCreateUser() {
				return createUser;
			}
			public void setCreateUser(String createUser) {
				this.createUser = createUser;
			}
			public String getCreateDate() {
				return createDate;
			}
			public void setCreateDate(String createDate) {
				this.createDate = createDate;
			}
			public String getErpOrderLine() {
				return erpOrderLine;
			}
			public void setErpOrderLine(String erpOrderLine) {
				this.erpOrderLine = erpOrderLine;
			}
			public String getDesciption() {
				return desciption;
			}
			public void setDesciption(String desciption) {
				this.desciption = desciption;
			}
			public String getUnitName() {
				return unitName;
			}
			public void setUnitName(String unitName) {
				this.unitName = unitName;
			}
			public String getItemMemo() {
				return itemMemo;
			}
			public void setItemMemo(String itemMemo) {
				this.itemMemo = itemMemo;
			}
			@Override
			public String toString() {
				return "SubErpOrderDetail [sid=" + sid + ", erpOrderSid=" + erpOrderSid + ", productId=" + productId
						+ ", productName=" + productName + ", unit=" + unit + ", count=" + count + ", salePrice="
						+ salePrice + ", discountPrice=" + discountPrice + ", sumAmount=" + sumAmount + ", createUser="
						+ createUser + ", createDate=" + createDate + ", erpOrderLine=" + erpOrderLine + ", desciption="
						+ desciption + ", unitName=" + unitName + ", itemMemo=" + itemMemo + "]";
			}
		}
	}
}
