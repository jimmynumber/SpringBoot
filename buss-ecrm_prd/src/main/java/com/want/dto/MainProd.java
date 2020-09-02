package com.want.dto;

import java.io.Serializable;

public class MainProd  implements Serializable{
	private static final long serialVersionUID = 1L;
	private String productCode;    //物料编号
	private String productName;    //物料描述（名称）
	private String prdShortName;   //产品短名称
	private String baseUnit;       //基本单位
	private String baseUnitName;   //基本单位描述
	private String baseUnitWeight; //基本单位重量
	private String tax;            //税率
	private String storage;        //总货架寿命
	private String outerCode;      //外箱条码
	private String price;          //标准零售价
	private String status;         //是否冻结
	private String imp;            //重点产品
	private String unit;           //产品单位
	private String unitName;       //产品单位描述
	private String denominator;    //单位转换-分母
	private String numerator;      //单位转换-分子
	private String simpleCode;     //物料条码
	private String unitWeight;     //单位重量
	private String lv1Id;          //产品大类编码
	private String lv1Name;        //产品大类名称
	private String lv2Id;          //产品系列编码
	private String lv2Name;        //产品系列名称
	private String lv3Id;          //产品线别编码
	private String lv3Name;        //产品线别名称
	private String lv4Id;          //产品规格编码
	private String lv4Name;        //产品规格名称
	private String lv5Id;          //规格包装组编码
	private String lv5Name;        //规格包装组名称
	private String updateDate;     //最后更新时间
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrdShortName() {
		return prdShortName;
	}
	public void setPrdShortName(String prdShortName) {
		this.prdShortName = prdShortName;
	}
	public String getBaseUnit() {
		return baseUnit;
	}
	public void setBaseUnit(String baseUnit) {
		this.baseUnit = baseUnit;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public String getBaseUnitWeight() {
		return baseUnitWeight;
	}
	public void setBaseUnitWeight(String baseUnitWeight) {
		this.baseUnitWeight = baseUnitWeight;
	}
	public String getTax() {
		return tax;
	}
	public void setTax(String tax) {
		this.tax = tax;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public String getOuterCode() {
		return outerCode;
	}
	public void setOuterCode(String outerCode) {
		this.outerCode = outerCode;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getImp() {
		return imp;
	}
	public void setImp(String imp) {
		this.imp = imp;
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
	public String getDenominator() {
		return denominator;
	}
	public void setDenominator(String denominator) {
		this.denominator = denominator;
	}
	public String getNumerator() {
		return numerator;
	}
	public void setNumerator(String numerator) {
		this.numerator = numerator;
	}
	public String getSimpleCode() {
		return simpleCode;
	}
	public void setSimpleCode(String simpleCode) {
		this.simpleCode = simpleCode;
	}
	public String getUnitWeight() {
		return unitWeight;
	}
	public void setUnitWeight(String unitWeight) {
		this.unitWeight = unitWeight;
	}
	public String getLv1Id() {
		return lv1Id;
	}
	public void setLv1Id(String lv1Id) {
		this.lv1Id = lv1Id;
	}
	public String getLv1Name() {
		return lv1Name;
	}
	public void setLv1Name(String lv1Name) {
		this.lv1Name = lv1Name;
	}
	public String getLv2Id() {
		return lv2Id;
	}
	public void setLv2Id(String lv2Id) {
		this.lv2Id = lv2Id;
	}
	public String getLv2Name() {
		return lv2Name;
	}
	public void setLv2Name(String lv2Name) {
		this.lv2Name = lv2Name;
	}
	public String getLv3Id() {
		return lv3Id;
	}
	public void setLv3Id(String lv3Id) {
		this.lv3Id = lv3Id;
	}
	public String getLv3Name() {
		return lv3Name;
	}
	public void setLv3Name(String lv3Name) {
		this.lv3Name = lv3Name;
	}
	public String getLv4Id() {
		return lv4Id;
	}
	public void setLv4Id(String lv4Id) {
		this.lv4Id = lv4Id;
	}
	public String getLv4Name() {
		return lv4Name;
	}
	public void setLv4Name(String lv4Name) {
		this.lv4Name = lv4Name;
	}
	public String getLv5Id() {
		return lv5Id;
	}
	public void setLv5Id(String lv5Id) {
		this.lv5Id = lv5Id;
	}
	public String getLv5Name() {
		return lv5Name;
	}
	public void setLv5Name(String lv5Name) {
		this.lv5Name = lv5Name;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "MainProd [productCode=" + productCode + ", productName=" + productName + ", prdShortName="
				+ prdShortName + ", baseUnit=" + baseUnit + ", baseUnitName=" + baseUnitName + ", baseUnitWeight="
				+ baseUnitWeight + ", tax=" + tax + ", storage=" + storage + ", outerCode=" + outerCode + ", price="
				+ price + ", status=" + status + ", imp=" + imp + ", unit=" + unit + ", unitName=" + unitName
				+ ", denominator=" + denominator + ", numerator=" + numerator + ", simpleCode=" + simpleCode
				+ ", unitWeight=" + unitWeight + ", lv1Id=" + lv1Id + ", lv1Name=" + lv1Name + ", lv2Id=" + lv2Id
				+ ", lv2Name=" + lv2Name + ", lv3Id=" + lv3Id + ", lv3Name=" + lv3Name + ", lv4Id=" + lv4Id
				+ ", lv4Name=" + lv4Name + ", lv5Id=" + lv5Id + ", lv5Name=" + lv5Name + ", updateDate=" + updateDate
				+ "]";
	}
	
}
