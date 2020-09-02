package com.want.dto;

import java.io.Serializable;
import java.util.List;
/**
    * 采购订单信息
 * @author 00320558 
 * @date   2019年09月16日
 */
public class ErpOrderDto implements Serializable {
	private static final long serialVersionUID = 4432339573970835249L;
	private String  sid;                     //主键sid
	private String  orderCode;               //采购订单编号
	private String  orderStatus;             //转销售单状态
	private String  feedbackInfor;           //SAP反馈信息
	private String  orderTime;               //订单日期
	private String  saleCompnayId;           //销售组织编码
	private String  saleCompnayName;         //销售组织名称
	private String  distributionChannelId;   //分销渠道编码
	private String  distributionChannelName; //分销渠道名称
	private String  productGroupId;          //产品组编码
	private String  productGroupName;        //产品组名称
	private String  companyId;               //分公司编码
	private String  companyName;             //分公司名称
	private String  sapBuyerId;              //售达方编码
	private String  sapBuyerName;            //售达方名称
	private String  sapReceiverId;           //送达方编码
	private String  sapReceiverName;         //送达方名称
	private String  memo;                    //订单备注
	private String  totalAmount;             //订单金额
	private String  desciption;              //原因描述
	private String  updateUser;              //操作者
	private String  updateDate;              //操作时间
	private String  openOrderDate;           //采购日期
	private String  orderType;               //订单类别 1:普通产品订单,2:促销组合订单   
	private String  rebateAmount;            //返利金额
	private String  remainRebateAmount;      //剩余返利金额
	private String  orderRebateAmount;      //订单返利金额
	// 20191119 00320558 begin add 
	private String  auart;	                  //订单类型 "标准订单类型:ZORF,样赠订单类型:ZFD."
	private String  augru;	                  //订单原因 "如果订单类型为ZFD,不可为空"
	private String  kostl;	                  //成本中心 "如果订单类型为ZFD,不可为空"
	// 20191119 00320558 end  
	private List<ErpOrderDetailDto> purchaseOrederInfoDetailList; // 一对多   采购订单信息详情
	public ErpOrderDto() {
		//this.purchaseOrederInfoDetailList = new ArrayList<PurchaseOrederInfoDetailDto>();
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getFeedbackInfor() {
		return feedbackInfor;
	}
	public void setFeedbackInfor(String feedbackInfor) {
		this.feedbackInfor = feedbackInfor;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getSaleCompnayName() {
		return saleCompnayName;
	}
	public void setSaleCompnayName(String saleCompnayName) {
		this.saleCompnayName = saleCompnayName;
	}
	public String getDistributionChannelName() {
		return distributionChannelName;
	}
	public void setDistributionChannelName(String distributionChannelName) {
		this.distributionChannelName = distributionChannelName;
	}
	public String getProductGroupName() {
		return productGroupName;
	}
	public void setProductGroupName(String productGroupName) {
		this.productGroupName = productGroupName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getSapBuyerName() {
		return sapBuyerName;
	}
	public void setSapBuyerName(String sapBuyerName) {
		this.sapBuyerName = sapBuyerName;
	}
	public String getSapReceiverName() {
		return sapReceiverName;
	}
	public void setSapReceiverName(String sapReceiverName) {
		this.sapReceiverName = sapReceiverName;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public List<ErpOrderDetailDto> getPurchaseOrederInfoDetailList() {
		return purchaseOrederInfoDetailList;
	}
	public void setPurchaseOrederInfoDetailList(List<ErpOrderDetailDto> purchaseOrederInfoDetailList) {
		this.purchaseOrederInfoDetailList = purchaseOrederInfoDetailList;
	}
	public String getSaleCompnayId() {
		return saleCompnayId;
	}
	public void setSaleCompnayId(String saleCompnayId) {
		this.saleCompnayId = saleCompnayId;
	}
	public String getDistributionChannelId() {
		return distributionChannelId;
	}
	public void setDistributionChannelId(String distributionChannelId) {
		this.distributionChannelId = distributionChannelId;
	}
	public String getProductGroupId() {
		return productGroupId;
	}
	public void setProductGroupId(String productGroupId) {
		this.productGroupId = productGroupId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getSapBuyerId() {
		return sapBuyerId;
	}
	public void setSapBuyerId(String sapBuyerId) {
		this.sapBuyerId = sapBuyerId;
	}
	public String getSapReceiverId() {
		return sapReceiverId;
	}
	public void setSapReceiverId(String sapReceiverId) {
		this.sapReceiverId = sapReceiverId;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	public String getDesciption() {
		return desciption;
	}
	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getOpenOrderDate() {
		return openOrderDate;
	}
	public void setOpenOrderDate(String openOrderDate) {
		this.openOrderDate = openOrderDate;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getRebateAmount() {
		return rebateAmount;
	}
	public void setRebateAmount(String rebateAmount) {
		this.rebateAmount = rebateAmount;
	}
	public String getRemainRebateAmount() {
		return remainRebateAmount;
	}
	public void setRemainRebateAmount(String remainRebateAmount) {
		this.remainRebateAmount = remainRebateAmount;
	}
	public String getOrderRebateAmount() {
		return orderRebateAmount;
	}
	public void setOrderRebateAmount(String orderRebateAmount) {
		this.orderRebateAmount = orderRebateAmount;
	}
	public String getAuart() {
		return auart;
	}
	public void setAuart(String auart) {
		this.auart = auart;
	}
	public String getAugru() {
		return augru;
	}
	public void setAugru(String augru) {
		this.augru = augru;
	}
	public String getKostl() {
		return kostl;
	}
	public void setKostl(String kostl) {
		this.kostl = kostl;
	}
	@Override
	public String toString() {
		return "ErpOrderDto [sid=" + sid + ", orderCode=" + orderCode + ", orderStatus=" + orderStatus
				+ ", feedbackInfor=" + feedbackInfor + ", orderTime=" + orderTime + ", saleCompnayId=" + saleCompnayId
				+ ", saleCompnayName=" + saleCompnayName + ", distributionChannelId=" + distributionChannelId
				+ ", distributionChannelName=" + distributionChannelName + ", productGroupId=" + productGroupId
				+ ", productGroupName=" + productGroupName + ", companyId=" + companyId + ", companyName=" + companyName
				+ ", sapBuyerId=" + sapBuyerId + ", sapBuyerName=" + sapBuyerName + ", sapReceiverId=" + sapReceiverId
				+ ", sapReceiverName=" + sapReceiverName + ", memo=" + memo + ", totalAmount=" + totalAmount
				+ ", desciption=" + desciption + ", updateUser=" + updateUser + ", updateDate=" + updateDate
				+ ", openOrderDate=" + openOrderDate + ", orderType=" + orderType + ", auart=" + auart + ", augru="
				+ augru + ", kostl=" + kostl + ", purchaseOrederInfoDetailList=" + purchaseOrederInfoDetailList + "]";
	}
}