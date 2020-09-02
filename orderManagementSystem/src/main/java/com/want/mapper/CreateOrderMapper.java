package com.want.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.want.dto.Customer;
import com.want.dto.CustomerReceverAddresses;
import com.want.dto.ProductInfoDto;
import com.want.dto.ProductLevelDto;
import com.want.po.PackageInfo;
import com.want.po.PromoteInfo;
import com.want.po.SthPromote;

@Mapper
public interface CreateOrderMapper { 
	
	public List<PackageInfo> packageList(@Param("saleCompany")String saleCompany,@Param("distributionChannel")String distributionChannel,@Param("productGroup")String productGroup);
	
	public List<CustomerReceverAddresses> customerReceverAddressesList(@Param("customerCode")String customerCode);
	
	public List<Customer> customerList(@Param("userId")String userId,@Param("customerId")String customerId,@Param("customerName")String customerName);
	
	public List<PromoteInfo> promoteList(@Param("productCode")String productCode,@Param("distributionChannel")String distributionChannel,@Param("productGroup")String productGroup,@Param("sapBuyerCode")String sapBuyerCode);
	
	public Double getProductPrice(@Param("saleCompany")String saleCompany,@Param("distributionChannel")String distributionChannel,@Param("productGroup")String productGroup,@Param("productCode")String productCode,@Param("customerCode")String customerCode);     
	
	// 查询品项信息
	List<ProductInfoDto> queryProdByCondition(Map<String, String> map);
	
	//获取产品大类和产品线别
	List<ProductLevelDto> getProductLevel(Map<String, String> map);
	
	public void updateErpOrderSumAmount(@Param("sid")String sid);
	
	List<SthPromote> sthPromote(@Param("productCode")String productCode,@Param("orderTime")String orderTime);
	
	List<Map<String, String>> historicalOrder(@Param("sapBuyerCode")String sapBuyerCode,@Param("beginDate")String beginDate,@Param("endDate")String endDate,@Param("orderComSid")String orderComSid);
	
	List<Map<String, String>> kanClassDetail(@Param("kanClassSid")String kanClassSid);
	
	public Double getSthProductPrice(@Param("productCode")String productCode);
	
} 