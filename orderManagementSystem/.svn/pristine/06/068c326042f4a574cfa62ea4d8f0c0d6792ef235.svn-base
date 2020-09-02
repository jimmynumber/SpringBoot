package com.want.service;

import java.util.List;
import java.util.Map;

import com.want.dto.Customer;
import com.want.dto.ProductInfoDto;
import com.want.dto.ProductLevelDto;
import com.want.po.PackageInfo;
import com.want.po.PromoteInfo;
import com.want.vo.ErpOrderDetailVo;

public interface ICreateOrderService {

	public List<PackageInfo> packageList(String saleCompany,String distributionChannel,String productGroup);
	
	public List<Customer> customerList(String userId,String customerId,String customerName);

	public Double getProductPrice(String saleCompany, String distributionChannel, String productGroup,
			String productCode, String sap_BUYER_CODE);

	public PromoteInfo validationPromote(String sap_BUYER_CODE, String productCode,
			String distributionChannel, String productGroup);
	
	// 查询品项信息
	List<ProductInfoDto> queryProdByCondition(Map<String, String> map);

	List<ProductInfoDto> queryProdPrice(Map<String, String> map);
	
	//获取产品大类和产品线别
	List<ProductLevelDto> getProductLevel(Map<String, String> map);
	
	public Map<String, String> itemGift(ErpOrderDetailVo erpOrderDetailVo);

}
