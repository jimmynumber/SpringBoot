package com.want.po;
import java.io.Serializable;
import java.util.Date;


public class ProductAssign implements Serializable{

    private static final long serialVersionUID = 2120869894112984147L;
    
    private String saleCompany;
    private String distributionChannel;
    private String productGroup;
    private String ProductCode;
    private Date createDate;
    private Date updateDate;
	
	public String getSaleCompany() {
		return saleCompany;
	}
	public void setSaleCompany(String saleCompany) {
		this.saleCompany = saleCompany;
	}
	public String getDistributionChannel() {
		return distributionChannel;
	}
	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}
	public String getProductGroup() {
		return productGroup;
	}
	public void setProductGroup(String productGroup) {
		this.productGroup = productGroup;
	}
	public String getProductCode() {
		return ProductCode;
	}
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    
	
    
}