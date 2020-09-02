package com.want.vo;

import java.io.Serializable;
/**
 * 产品结构 对接接口
 * @author 00320558
 * 2019-12-23 11:20:00
 */
public class ProdStruct implements Serializable {

	private static final long serialVersionUID = 3989316113677191333L;
	private String id;                   //主键
	private String productStructureCode; //产品结构编码
	private String productStructureName; //产品结构名称
	private String createTime;           //创建时间
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductStructureCode() {
		return productStructureCode;
	}
	public void setProductStructureCode(String productStructureCode) {
		this.productStructureCode = productStructureCode;
	}
	public String getProductStructureName() {
		return productStructureName;
	}
	public void setProductStructureName(String productStructureName) {
		this.productStructureName = productStructureName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "ProdStruct [id=" + id + ", productStructureCode=" + productStructureCode + ", productStructureName="
				+ productStructureName + ", createTime=" + createTime + "]";
	}
}
