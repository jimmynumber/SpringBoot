package com.want.po;
import java.io.Serializable;


public class ProductInfo implements Serializable{

    private static final long serialVersionUID = 2120869894112984147L;

    private String productName;
    private String unitName;
    private String unit;
    
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
    
	
}