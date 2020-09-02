package com.want.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.dto.Customer;
import com.want.dto.CustomerReceverAddresses;
import com.want.dto.ProductInfoDto;
import com.want.dto.ProductLevelDto;
import com.want.mapper.CreateOrderMapper;
import com.want.mapper.OrderMapper;
import com.want.po.ERPOrderDetail;
import com.want.po.PackageInfo;
import com.want.po.ProductInfo;
import com.want.po.PromoteInfo;
import com.want.service.ICreateOrderService;
import com.want.vo.ErpOrderDetailVo;
import com.want.vo.ErpOrderDetailVo.SubErpOrderDetail;

@Service 
public class CreateOrderServiceImpl implements ICreateOrderService{
	private static final Logger LOGGER = LoggerFactory.getLogger(CreateOrderServiceImpl.class);
	
	@Autowired
    private OrderMapper orderMapper;
	
	@Autowired
    private CreateOrderMapper createOrderMapper;
	
	public List<PackageInfo> packageList(String saleCompany,String distributionChannel,String productGroup){
		return createOrderMapper.packageList(saleCompany,distributionChannel,productGroup);
	}
	
	public List<Customer> customerList(String userId,String customerId,String customerName){
		List<Customer> customerList = createOrderMapper.customerList(userId, customerId, customerName);
		if(customerList.size()>0&&customerList!=null) {
			for(int i=0;i<customerList.size();i++) {
				Customer customer = customerList.get(i);
				List<CustomerReceverAddresses> customerReceverAddresses = createOrderMapper.customerReceverAddressesList(customer.getCustomerCode());
				customer.setCustomerReceverAddresses(customerReceverAddresses);
			}
		}
		return customerList;
	}
	
	// 查询品项价格和促销信息
	@Override
	public List<ProductInfoDto> queryProdPrice(Map<String, String> map) {
		LOGGER.info("CreateOrderServiceImpl.queryProdByCondition map:{} ",map);
		String customerCode=map.get("customerCode");
		String saleCompnayId=map.get("saleCompnayId");
		String distributionChannelId=map.get("distributionChannelId");
		String productGroupId=map.get("productGroupId");
		String addWay=map.get("addWay");
		if(addWay != null && "2".equals(addWay)) {
			map.put("customerCode", "");
		}
		List<ProductInfoDto> list=createOrderMapper.queryProdByCondition(map);
		for (Iterator<ProductInfoDto> iterator = list.iterator(); iterator.hasNext();) {
			ProductInfoDto productInfoDto = iterator.next();
			String productCode=productInfoDto.getProductCode();
			try {
				//根据条件查询产品是否有且符合的促销政策
				PromoteInfo promote = this.validationPromote(customerCode,productCode,distributionChannelId,productGroupId);
				if( promote != null ) {
					productInfoDto.setPromotePolicyDesc(promote.getPromotePolicyDesc());
					productInfoDto.setGiveProdId(promote.getGiveProdId());
					productInfoDto.setSalesPrice(promote.getSalePrice());
					productInfoDto.setPromoteCount(promote.getPromoteCount());
					productInfoDto.setPromoteUnit(promote.getPromoteUnit());
					productInfoDto.setGiveCount(promote.getGiveCount());
					productInfoDto.setGiveUnit(promote.getGiveUnit());
			    }else {
			    	//普通产品价格查询
			    	Double price = (double) 0;
					price = createOrderMapper.getSthProductPrice(productCode);
					if( price == null) {
						price = this.getProductPrice(saleCompnayId,distributionChannelId,productGroupId,productCode,customerCode);
					}
					if( price == null) {
						//iterator.remove();  价格为0也显示，因而注释掉
						productInfoDto.setSalesPrice(0.0);
					}else {
						productInfoDto.setSalesPrice(price);
					}
				}
			} catch (Exception e) {
				LOGGER.error(e.toString());
				iterator.remove();
			}
		}
		return list;
	}
	
	//普通产品价格查询
	//取值逻辑根据-客户别价格（销售组织+渠道+物料+客户）>渠道产品组别价格（销售组织+渠道+产品组+物料）  >渠道别价格（销售组织+渠道+物料)
	//价格取有效的且在当前时间段内的
	public Double getProductPrice(String saleCompany,String distributionChannel,String productGroup,
			String productCode,String customerCode){ 
		Double price = createOrderMapper.getProductPrice(saleCompany,distributionChannel,"",productCode,customerCode);
		if(price==null) {
			price = createOrderMapper.getProductPrice(saleCompany,distributionChannel,productGroup,productCode,"");
		}
		if(price==null) {
			price = createOrderMapper.getProductPrice(saleCompany,distributionChannel,"",productCode,"");
		}
		return price;
	}
	
	//根据条件查询产品是否有且符合的促销政策
	public PromoteInfo validationPromote(String customerCode,String productCode,
			String distributionChannel,String productGroup) { 
		PromoteInfo promote = null;
		List<PromoteInfo> promotes = createOrderMapper.promoteList(productCode,distributionChannel,productGroup,customerCode);
		if(promotes!=null&&promotes.size()==1) {
			promote = promotes.get(0);
		}
		return promote;
	}

	// 查询品项信息
	@Override
	public List<ProductInfoDto> queryProdByCondition(Map<String, String> map) {
		LOGGER.info("CreateOrderServiceImpl.queryProdByCondition map:{} ",map);
		return createOrderMapper.queryProdByCondition(map);
	}

	//获取产品大类和产品线别
	@Override
	public List<ProductLevelDto> getProductLevel(Map<String, String> map) {
		LOGGER.info("CreateOrderServiceImpl.getProductLevel map:{} ",map);
		return createOrderMapper.getProductLevel(map);
	}
	
	public Map<String, String> itemGift(ErpOrderDetailVo erpOrderDetailVo){
		Map<String, String> otReturn= new HashMap<String, String>();
		Date date=new Date();
		List<ERPOrderDetail> erpOrderDetailList =new ArrayList<>();
		
		try {
			String saleCompnayId=erpOrderDetailVo.getSaleCompnayId();
			String distributionChannelId=erpOrderDetailVo.getDistributionChannelId();
			String productGroupId=erpOrderDetailVo.getProductGroupId();
			String createUser=erpOrderDetailVo.getEmpId()+"-"+erpOrderDetailVo.getEmpName();
			String erpOrderSid=erpOrderDetailVo.getErpOrderSid();
			List<SubErpOrderDetail> giftErpOrderDetails = erpOrderDetailVo.getErpOrderDetails();
			
			if(giftErpOrderDetails != null && giftErpOrderDetails.size()>0) {
				for(SubErpOrderDetail giftErpOrderDetail:giftErpOrderDetails) {
					if(giftErpOrderDetail.getSid() == null || "".equals(giftErpOrderDetail.getSid())) {
						ERPOrderDetail erpOrderDetail = new ERPOrderDetail();
						String productId=giftErpOrderDetail.getProductId();
						String count=giftErpOrderDetail.getCount();
						
						Double price = this.getProductPrice(saleCompnayId,distributionChannelId,productGroupId,productId,"");
						if( price == null) {
							price = (double) 0;
						}
						List<ProductInfo> product = orderMapper.queryUnitByProductCode(productId);
						if(product!=null&&product.size()>0) {
							erpOrderDetail.setUnitName(product.get(0).getUnitName());
							erpOrderDetail.setProductName(product.get(0).getProductName());
							erpOrderDetail.setUnit(product.get(0).getUnit());
						}else {
							erpOrderDetail.setUnitName("");
							erpOrderDetail.setProductName("");
							erpOrderDetail.setUnit("");
						}
						erpOrderDetail.setErpOrderSid(erpOrderSid);
						erpOrderDetail.setCount(Double.valueOf(count));
						erpOrderDetail.setProductId(productId);
						erpOrderDetail.setSalePrice(price);
						erpOrderDetail.setDiscountPrice((double) 0);
						erpOrderDetail.setRebateDiscountPrice((double) 0);
						erpOrderDetail.setSumAmount((double) 0);
						erpOrderDetail.setPromoteId("");
						erpOrderDetail.setPromotePolicyDesc("");
						erpOrderDetail.setCreateUser(createUser);
						erpOrderDetail.setCreateDate(date);
						erpOrderDetail.setOrderLineType("ZTNN");
						erpOrderDetail.setAddWay("2");
						erpOrderDetailList.add(erpOrderDetail);
					}
				}
				if(erpOrderDetailList != null && erpOrderDetailList.size()>0) {
					orderMapper.insertERPOrderDetail(erpOrderDetailList);
				}
				otReturn.put("type", "S");
				otReturn.put("message", "添加成功");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			otReturn.put("type", "F");
			otReturn.put("message", "添加失败");
		}
		return otReturn;
	}
	
}