package com.want.dto;

import java.io.Serializable;
/**
 * 采购订单信息详情
 * @author 00320558 
 * @date   2019年09月16日
 */
public class ErpOrderDetailDto implements Serializable {
	private static final long serialVersionUID = 5847153539227958372L;
	private String  lineNumber;    //订单行号
	private String  productId;     //产品编码
	private String  productName;   //产品名称
	private String  stockAddresId; //库存地点编码
	private String  stockAddres;   //库存地点
	private String  salePrice;     //开单价
	private String  discountPrice; //折后单价
	private String  rebateDiscountPrice; //返利折后单价
	private String  count;         //数量
	private String  unit;          //单位
	private String  unitName;      //单位名称
	private String  sumAmount;     //行金额合计
	private String  itemMemo;      //行备注
	// 20191119 00320558 begin add 
	private String orderLineType;      //pstyv  订单行项目类型 "标准行项目类别:ZTFN,搭赠项目类别:ZTNN,样赠行项目类别:ZKLN"
	private String promoteId;          //tmpId  促销案编号	   		
	private String promotePolicyDesc;  //tmpText 促销案说明	   		
	// 20191119 00320558 end   
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
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
	public String getStockAddres() {
		return stockAddres;
	}
	public void setStockAddres(String stockAddres) {
		this.stockAddres = stockAddres;
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
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSumAmount() {
		return sumAmount;
	}
	public void setSumAmount(String sumAmount) {
		this.sumAmount = sumAmount;
	}
	public String getItemMemo() {
		return itemMemo;
	}
	public void setItemMemo(String itemMemo) {
		this.itemMemo = itemMemo;
	}
	public String getStockAddresId() {
		return stockAddresId;
	}
	public void setStockAddresId(String stockAddresId) {
		this.stockAddresId = stockAddresId;
	}
	public String getOrderLineType() {
		return orderLineType;
	}
	public void setOrderLineType(String orderLineType) {
		this.orderLineType = orderLineType;
	}
	public String getPromoteId() {
		return promoteId;
	}
	public void setPromoteId(String promoteId) {
		this.promoteId = promoteId;
	}
	public String getPromotePolicyDesc() {
		return promotePolicyDesc;
	}
	public void setPromotePolicyDesc(String promotePolicyDesc) {
		this.promotePolicyDesc = promotePolicyDesc;
	}
	@Override
	public String toString() {
		return "ErpOrderDetailDto [lineNumber=" + lineNumber + ", productId=" + productId + ", productName="
				+ productName + ", stockAddresId=" + stockAddresId + ", stockAddres=" + stockAddres + ", salePrice="
				+ salePrice + ", discountPrice=" + discountPrice + ", count=" + count + ", unit=" + unit + ", unitName="
				+ unitName + ", sumAmount=" + sumAmount + ", itemMemo=" + itemMemo + ", orderLineType=" + orderLineType
				+ ", promoteId=" + promoteId + ", promotePolicyDesc=" + promotePolicyDesc + "]";
	}
	
}