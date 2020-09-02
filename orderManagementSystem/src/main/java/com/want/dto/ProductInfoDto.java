package com.want.dto;

import java.io.Serializable;
/**
 * 促销组合和一般订单 品项信息
 * @author 00320558 
 * @date   2019年11月12日
 */
public class ProductInfoDto implements Serializable{
	private static final long serialVersionUID = 5350655627982007231L;
	private String productCode;       //物料编号
	private String prdShortName;      //物料短名称
	private String  productName;      //产品名称
	private String  stockAddresId;    //库存地点编码
	private String  stockAddres;      //库存地点
	private String unit;              //基本单位
	private String unitName;          //基本单位描述
	private String promotePolicyDesc; //促销产品ID
	private String giveProdId;        //促销政策说明
	private Double salesPrice;        //开单价
	private Double promoteCount;      //促销数量
	private String promoteUnit;       //促销单位
	private Double giveCount;         //赠品数量
	private String giveUnit;          //赠品单位
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getPrdShortName() {
		return prdShortName;
	}
	public void setPrdShortName(String prdShortName) {
		this.prdShortName = prdShortName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getStockAddresId() {
		return stockAddresId;
	}
	public void setStockAddresId(String stockAddresId) {
		this.stockAddresId = stockAddresId;
	}
	public String getStockAddres() {
		return stockAddres;
	}
	public void setStockAddres(String stockAddres) {
		this.stockAddres = stockAddres;
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
	public String getPromotePolicyDesc() {
		return promotePolicyDesc;
	}
	public void setPromotePolicyDesc(String promotePolicyDesc) {
		this.promotePolicyDesc = promotePolicyDesc;
	}
	public String getGiveProdId() {
		return giveProdId;
	}
	public void setGiveProdId(String giveProdId) {
		this.giveProdId = giveProdId;
	}
	public Double getSalesPrice() {
		return salesPrice;
	}
	public void setSalesPrice(Double salesPrice) {
		this.salesPrice = salesPrice;
	}
	public Double getPromoteCount() {
		return promoteCount;
	}
	public void setPromoteCount(Double promoteCount) {
		this.promoteCount = promoteCount;
	}
	public String getPromoteUnit() {
		return promoteUnit;
	}
	public void setPromoteUnit(String promoteUnit) {
		this.promoteUnit = promoteUnit;
	}
	public Double getGiveCount() {
		return giveCount;
	}
	public void setGiveCount(Double giveCount) {
		this.giveCount = giveCount;
	}
	public String getGiveUnit() {
		return giveUnit;
	}
	public void setGiveUnit(String giveUnit) {
		this.giveUnit = giveUnit;
	}
	@Override
	public String toString() {
		return "ProductInfoDto [productCode=" + productCode + ", prdShortName=" + prdShortName + ", unit=" + unit
				+ ", unitName=" + unitName + ", promotePolicyDesc=" + promotePolicyDesc + ", giveProdId=" + giveProdId
				+ ", salesPrice=" + salesPrice + ", promoteCount=" + promoteCount + ", promoteUnit=" + promoteUnit
				+ ", giveCount=" + giveCount + ", giveUnit=" + giveUnit + "]";
	}
}
