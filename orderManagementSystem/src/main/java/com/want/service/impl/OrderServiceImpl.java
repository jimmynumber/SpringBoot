package com.want.service.impl;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.dto.AttributeInfo;
import com.want.dto.ErpOrderDetailDto;
import com.want.dto.ErpOrderDto;
import com.want.dto.OrderDetailDto;
import com.want.dto.OrderDto;
import com.want.dto.OrderInfoDto;
import com.want.dto.Organization;
import com.want.mapper.CreateOrderMapper;
import com.want.mapper.OrderMapper;
import com.want.po.PackageInfo;
import com.want.service.ICreateOrderService;
import com.want.service.IOrderService;
import com.want.util.WebServiceUtil;
import com.want.vo.ErpOrder;
import com.want.vo.ErpOrder.SubErpOrder;
import com.want.vo.ErpOrderDetailVo;
import com.want.vo.ErpOrderDetailVoList;
import com.want.vo.ErpOrderDetailVoList.SubErpOrderList;
import com.want.vo.ErpOrderDetailVoList.SubErpOrderList.SubErpOrderDetailList;
import com.want.vo.ErpOrderDetailVo.SubErpOrderDetail;
import com.want.vo.Order;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service 
@Transactional
public class OrderServiceImpl implements IOrderService{
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
    private OrderMapper orderMapper;
	
	@Autowired
    private CreateOrderMapper createOrderMapper;
	
	@Autowired
    private ICreateOrderService createOrderService;
	
	List<Organization> orgList=null;
	public List<Organization> orgList(String empId) {
		List<Organization> list=new ArrayList<>();
		orgList=null;
		//根据人员查询所属组织及下级组织
		orgList=orderMapper.orgList(empId); 
		if(orgList!=null&&orgList.size()>0) {
			for(Organization organization: orgList) {
				//获取人员最高级组织节点
				Organization org=orgList.get(0);
				if(organization.getOrgLevel().equals(org.getOrgLevel())) {
					Organization orgJson=new Organization();
					orgJson.setOrgId(organization.getOrgId());
					orgJson.setOrgName(organization.getOrgName());
					orgJson.setOrgLevel(organization.getOrgLevel());
					orgJson.setParentOrgID(organization.getParentOrgID());
					list.add(orgJson);
					String orgId=organization.getOrgId();
					//根据最高级组织节点向下递归
					recursive(orgId,orgJson);
				}
			}
		}
		return list; 
	}
	
	public void recursive(String parentOrgID,Organization orgJson) { 
		List<Organization> list1=new ArrayList<>();
		//从1开始循环人员所属组织
		for(int i=1;i<orgList.size();i++) {
			Organization org=orgList.get(i);
			if(parentOrgID.equals(org.getParentOrgID())) {
				Organization childOrg=new Organization();
				childOrg.setOrgId(org.getOrgId());
				childOrg.setOrgName(org.getOrgName());
				childOrg.setOrgLevel(org.getOrgLevel());
				childOrg.setParentOrgID(org.getParentOrgID());
				list1.add(childOrg);
				String orgId=org.getOrgId();
				//子节点再递归查下级节点
				recursive(orgId,childOrg);
			}
		}
		//将父节点所属子节点集和赋值到父节点元素内
		orgJson.setChildOrg(list1);
	}
	
	public Map<String,Object> cancelOrder(String updateUser,String orderId,String cancelReason) {
		Map<String,Object> resultMap=new HashMap<>();
		try {
			long flag=orderMapper.queryERPOrderByCode(orderId);
			if(flag==0) {
				orderMapper.updateOrderStatusByOrderId(updateUser,orderId,cancelReason);
				//2019-11-12 取消订单要添加级联操作
				orderMapper.updateERPOrderStatusByOrderId(updateUser,orderId,cancelReason);
				resultMap.put("status", "C");
				resultMap.put("desciption", "取消成功");
			}else {
				resultMap.put("status", "S");
				resultMap.put("desciption", "该订单下有已处理的订单，无法取消");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resultMap.put("status", "ERROR");
			resultMap.put("desciption", "系统异常，取消失败");
		}
		return resultMap;
	}
	
	public List<PackageInfo> packageList(){
		return orderMapper.packageList();
	}
	
	@Override
	public List<OrderInfoDto> queryOrderByCondition(Order order) {
		if (null == order) {
			//return null;
		}
		return orderMapper.queryOrderByCondition(order);
	}

	@Override
	public OrderDto getOrderInfos(String orderCode) {
		if ( null == orderCode || orderCode.trim().length()<= 0) {
			//return null;
		}
		OrderDto item=orderMapper.getOrderInfos(orderCode);
		List<OrderDetailDto> subList=orderMapper.getOrderInfoDetails(item.getOrderCode());
		item.setNeedOrederInfoDetailDtoList(subList);
		return item;
	}
	
	@Override
	public List<ErpOrderDto> getErpOrders(String orderCode) {
		if ( null == orderCode || orderCode.trim().length()<= 0) {
			//return null;
		}
		 List<ErpOrderDto> orderList=orderMapper.getErpOrders(orderCode);
		 if(null != orderList && orderList.size()>0 ) {
			 for (ErpOrderDto item : orderList) {
				 item.setPurchaseOrederInfoDetailList(orderMapper.getErpOrderInfoDetails(item.getSid()));
			}
		 }		 
	    return orderList;
	}

	@Override
	@SuppressWarnings({ "unchecked", "finally" })
	public Map<String,Object> updateErpOrders(ErpOrderDetailVoList erpOrderDetailVoList) {
		Map<String,Object> resultMap=new HashMap<>();
		List<SubErpOrderList> erpOrderDetailVos = erpOrderDetailVoList.getErpOrders();
		if ( null == erpOrderDetailVos || erpOrderDetailVos.size()<= 0) {
			return null;
		}
		
		String currentDate=this.getCurrentDate();
		List<ErpOrderDto> reResult = new ArrayList<ErpOrderDto>();
		boolean isFirst=false;
		boolean isRebate=true;
		String orderCode="";
		BigDecimal amount=new BigDecimal("0");
		BigDecimal sumAmount=new BigDecimal("0");
		Map<String,String> rebateMap=new HashMap<>();
		String status = "";
		
		try {
			rebateMap.put("customerCode", erpOrderDetailVos.get(0).getCustomerCode());
			rebateMap.put("updateUser", erpOrderDetailVos.get(0).getEmpId()+"-"+erpOrderDetailVos.get(0).getEmpName());
			
			for (SubErpOrderList erpOrderDetailVo : erpOrderDetailVos) {
				if(erpOrderDetailVo.getOrderRebateAmount() != null && !"".equals(erpOrderDetailVo.getOrderRebateAmount()) && !"null".equals(erpOrderDetailVo.getOrderRebateAmount()) && !"0".equals(erpOrderDetailVo.getOrderRebateAmount())){
					sumAmount = sumAmount.add(new BigDecimal(erpOrderDetailVo.getOrderRebateAmount()));
				}
			}
			String RemainRebateAmount = orderMapper.validateRemainRebateAmount(erpOrderDetailVos.get(0).getCustomerCode());
			if(RemainRebateAmount == null) {
				RemainRebateAmount = "0";
				isRebate=false;
			}
			BigDecimal sumAmount1 = new BigDecimal(RemainRebateAmount);
			if(sumAmount.compareTo(sumAmount1) == 1){
				status = "2";
			}else{
				status = orderMapper.validateCustomerRebateStatus(erpOrderDetailVos.get(0).getCustomerCode());
				if(status == null || "0".equals(status)){
					rebateMap.put("status", "1");
					orderMapper.updateCustomerRebateStatus(rebateMap);
					
					for (SubErpOrderList erpOrderDetailVo : erpOrderDetailVos) {
						if(erpOrderDetailVo.getOrderRebateAmount() == null || "".equals(erpOrderDetailVo.getOrderRebateAmount()) || "null".equals(erpOrderDetailVo.getOrderRebateAmount()) || "0.00".equals(erpOrderDetailVo.getOrderRebateAmount()) || "0".equals(erpOrderDetailVo.getOrderRebateAmount())){
							isRebate=false;
						}else {
							isRebate=true;
						}
						
						 ErpOrderDto item=orderMapper.getErpOrderBySid(erpOrderDetailVo.getErpOrderSid());
						 if(null != item ) {
							if (!isFirst) {
								isFirst=true;
								orderCode=item.getOrderCode();
							}
							
							List<ErpOrderDetailDto> erpOrderDetailList=orderMapper.getErpOrderInfoDetails(item.getSid());
							for (SubErpOrderDetailList erpOrderDetail : erpOrderDetailVo.getErpOrderDetails()) {
								 String count=erpOrderDetail.getCount();
								 String discountPrice=erpOrderDetail.getDiscountPrice();
								 
								 if(erpOrderDetailVo.getOrderRebateAmount() != null && !"".equals(erpOrderDetailVo.getOrderRebateAmount())) {
									if( ! (count == null || discountPrice == null )) {
										BigDecimal price=(new BigDecimal(discountPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
										BigDecimal amount2=new BigDecimal(count).multiply(price);
										//>=0.5 进1 
										amount2.setScale(2, BigDecimal.ROUND_HALF_UP);
										
										for(ErpOrderDetailDto erpOrderDetailDto1:erpOrderDetailList){
											if(erpOrderDetailDto1.getLineNumber().equals(erpOrderDetail.getSid())){
												erpOrderDetailDto1.setRebateDiscountPrice(discountPrice);
												erpOrderDetailDto1.setSumAmount(amount2.toPlainString());
											}
										}
										
									}
								 }
							 }
							
							// 20191120 00320558 modify 只有订单状态 未处理的 也就是 null 或 E 才允许抛转
							String orderStatus=item.getOrderStatus();
							if ( orderStatus == null || "失败".equalsIgnoreCase(orderStatus) ) {
								// 传入采购订单日期
								item.setOpenOrderDate(erpOrderDetailVos.get(0).getOpenOrderDate());
								// 发送资料并接收返回值
						        Map<String, Object> retuenMap =WebServiceUtil.sendZRFCSD026(item, erpOrderDetailList);
						        // erp 返回订单头信息
						        Map<String, String> otvbakMap= (Map<String, String>) retuenMap.get("otvbakMap");
							    // 将erp 返回 erp订单编码，状态，错误原因根据订单编码更新
						        otvbakMap.put("empInfo", erpOrderDetailVo.getEmpId()+"-"+erpOrderDetailVo.getEmpName());
						        otvbakMap.put("currentDate", currentDate);
								orderMapper.updateErpOrder(otvbakMap);	    
							    // 将erp 返回 erp订单详情编码，状态，错误原因根据订单明细编码更新
								orderMapper.updateErpOrderDetail((List<Map<String, String>>) retuenMap.get("otvbapMap"));
								String erpOrderCode=otvbakMap.get("erpOrderCode");
								String desciption= otvbakMap.get("desciption");
								
								if ( erpOrderCode != null && erpOrderCode.trim().length()>0 ) {
									desciption=erpOrderCode;
									Map<String,String> paramMap=new HashMap<>();
									paramMap.put("sid", item.getSid());
									paramMap.put("openOrderDate", erpOrderDetailVos.get(0).getOpenOrderDate());
									orderMapper.updateErpOrder(paramMap);	
								}else {
									// 抛单失败 置"" 返回
									item.setOpenOrderDate("");
									isRebate=false;
								}
								item.setOrderStatus(otvbakMap.get("orderStatus"));
								item.setFeedbackInfor(desciption);
							} else {
								item.setOrderStatus("E");
								item.setFeedbackInfor("此订单状态为"+orderStatus+"状态,不可转单");
								isRebate=false;
							}
							
							if(isRebate){
								 amount = amount.add(new BigDecimal(erpOrderDetailVo.getOrderRebateAmount()));
								 rebateMap.put("amount", amount.toPlainString());
								 List<Map<String, String>> list=new ArrayList<>();
								 for (SubErpOrderDetailList erpOrderDetail : erpOrderDetailVo.getErpOrderDetails()) {
										Map<String,String> paramMap=new HashMap<String,String>();
										String count=erpOrderDetail.getCount();
										String discountPrice=erpOrderDetail.getDiscountPrice();
										
										if( ! (count == null || discountPrice == null )) {
											BigDecimal price=(new BigDecimal(discountPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
											BigDecimal amount1=new BigDecimal(count).multiply(price);
											//>=0.5 进1 
											amount1.setScale(2, BigDecimal.ROUND_HALF_UP);
											paramMap.put("sumAmount",amount1.toPlainString() );
										}
										paramMap.put("sid", erpOrderDetail.getSid());
										paramMap.put("rebateDiscountPrice", discountPrice);
										
										list.add(paramMap);
									}
									
									if (list != null && list.size()>0 ) {
										orderMapper.updateErpOrderDetail(list);
										Map<String,String> paramMap=new HashMap<String,String>();
										paramMap.put("sid", erpOrderDetailVo.getErpOrderSid());
								        paramMap.put("empInfo", erpOrderDetailVo.getEmpId()+"-"+erpOrderDetailVo.getEmpName());
								        paramMap.put("currentDate", currentDate);
								        orderMapper.updateErpOrderStatus(paramMap);
								        orderMapper.updateOrderRebateAmount(erpOrderDetailVo.getErpOrderSid(),erpOrderDetailVo.getOrderRebateAmount());
								        createOrderMapper.updateErpOrderSumAmount(erpOrderDetailVo.getErpOrderSid());
									}
								 orderMapper.insertCustomerRebateRecord(erpOrderDetailVos.get(0).getCustomerCode(), erpOrderDetailVo.getEmpId()+"-"+erpOrderDetailVo.getEmpName(), item.getSid(), erpOrderDetailVo.getOrderRebateAmount());
							 }
							
							item.setUpdateUser(erpOrderDetailVo.getEmpId()+"-"+erpOrderDetailVo.getEmpName());
							item.setUpdateDate(currentDate);
							item.setPurchaseOrederInfoDetailList(erpOrderDetailList);
							ErpOrderDto item1=orderMapper.getErpOrderBySid(erpOrderDetailVo.getErpOrderSid());
							item.setRebateAmount(item1.getRebateAmount());
							item.setRemainRebateAmount(item1.getRemainRebateAmount());
							item.setOrderRebateAmount(item1.getOrderRebateAmount());
							reResult.add(item);
						 }	
						 
					}
					
					// 查询拆分后的订单 若全部成功，更新order_info.order_status  为"已处理"
					if ( isFirst ) {
						this.changeOrderInfoStatus(orderCode);
					}
					resultMap.put("data", reResult);
					rebateMap.put("status", "0");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}finally {
			resultMap.put("status", status);
			orderMapper.updateCustomerRebateStatus(rebateMap);
			return resultMap;
		}
	}
	
	@Override
	public Map<String,Object> closeOrder(ErpOrder erpOrder) {
		if (erpOrder == null) {
			return null;
		}
		LOGGER.info("OrderServiceImpl.closeOrderTest ErpOrder:{} ",erpOrder.toString());
		Map<String,Object> resultMap=new HashMap<>();
		List<Map<String,String>> list= new ArrayList<>();
		String currentDate=this.getCurrentDate();
		 
		String orderCode=null; 
		List<SubErpOrder> erpOrders=erpOrder.getErpOrders();
		if ( null != erpOrders && erpOrders.size()>0) {
			for (SubErpOrder item : erpOrders) {
				String sid=item.getSid();
				String desciption=item.getDesciption();
				Map<String,String> map=new HashMap<>();
				map.put("sid", sid);
				// 根据erp_order 主键查询 订单状态
		        Map<String,String> orderStatusMap=orderMapper.checkErpOrderStatus(sid);
		        if (null == orderStatusMap || orderStatusMap.size() <= 0 ) {
		        	map.put("status", "失败");
		        	map.put("desciption", "找不到对应采购订单："+sid);
		        	list.add(map);
		        	continue;
				}
		        String orderStatus=orderStatusMap.get("ORDER_STATUS");
		        orderCode=orderStatusMap.get("ORDER_CODE");
		        //1）检查状态是否为null 或者 E（未处理过或抛失败的订单可以关闭）
		        if( !"未处理".equals(orderStatus)) {
		        	map.put("status", "失败");
		        	map.put("desciption", "未处理过或抛失败的订单可以关闭");
		        	list.add(map);
		        	continue;
		        }
		        map.put("status", "关闭");
				map.put("desciption", desciption);
				map.put("currentDate", currentDate);
				list.add(map);
				
				Map<String,String> paramMap=new HashMap<String,String>();
				paramMap.put("sid", sid);
		        paramMap.put("orderStatus", "C");
		        paramMap.put("desciption", desciption);
		        paramMap.put("empInfo", item.getEmpId()+"-"+item.getEmpName());
		        paramMap.put("currentDate", currentDate);
		        orderMapper.updateErpOrderStatus(paramMap);
			}
		}
		// 检查该笔订单对应所有的拆单状态是否都为【已关闭-C】，
		// 如果是：变更order_info.ORDER_STATUS状态为【已取消-C】
		String orderStatus = this.changeOrderInfoStatus(orderCode);
		resultMap.put("orderStatus", orderStatus);
		resultMap.put("erpOrders", list);
    	return resultMap;
	}
	
	// 根据 订单编码orderCode 更新 order_info 订单状态
	private String changeOrderInfoStatus(String orderCode) {
		//1. 若拆分后订单状态 全部为 "关闭" ， 则order_info 订单状态 为"已取消" 
		//2. 若拆分后订单状态 全部由 "成功"||"关闭" 组成， 则order_info 订单状态 为"已处理" 
		//3. 其余为 未处理
		Map<String,String> ErpOrderAllStatusMap=orderMapper.checkErpOrderAllStatus(orderCode);
		String orderStatus = ErpOrderAllStatusMap.get("ORDER_STATUS");
		String states="";
		if("已取消".equals(orderStatus)) {
			states="C";
		}else if ("已处理".equals(orderStatus)) {
			states="S";
		}else {
			//states="W";
			return orderStatus;
		}
		Map<String,String> paramMap=new HashMap<String,String>();
		paramMap.put("orderCode", orderCode);
		paramMap.put("orderStatus", states);
		orderMapper.updateOrderInfoStatus(paramMap);
		return orderStatus;
	}
	
	// 获取当前时间
	private  String getCurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");  
		return formatter.format( Calendar.getInstance().getTime());
	}

	@Override
	public List<AttributeInfo> getAttributeInfo(Map<String, String> map) {
		return orderMapper.getAttributeInfo(map);
	}

	@Override
	public Map<String,Object>  modifyErpOrderDetail(ErpOrderDetailVo erpOrderDetailVo) {
		String currentDate=this.getCurrentDate();
		Map<String,Object> resultMap=new HashMap<>();
		resultMap.put("currentDate", "");
		if( erpOrderDetailVo == null || erpOrderDetailVo.getErpOrderDetails().size() <= 0) {
			 return resultMap;
		}
		
		List<Map<String, String>> list=new ArrayList<>();
		for (SubErpOrderDetail erpOrderDetail : erpOrderDetailVo.getErpOrderDetails()) {
			if(erpOrderDetail.getSid() != null && !"".equals(erpOrderDetail.getSid())) {
				Map<String,String> paramMap=new HashMap<String,String>();
				String count=erpOrderDetail.getCount();
				String discountPrice=erpOrderDetail.getDiscountPrice();
				if( ! (count == null || discountPrice == null )) {
					BigDecimal price=(new BigDecimal(discountPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
					BigDecimal amount=new BigDecimal(count).multiply(price);
					//>=0.5 进1 
					amount.setScale(2, BigDecimal.ROUND_HALF_UP);
					paramMap.put("sumAmount",amount.toPlainString() );
				}
				paramMap.put("sid", erpOrderDetail.getSid());
				paramMap.put("count", count);
				paramMap.put("discountPrice", discountPrice);
				
				list.add(paramMap); 
			}
		}
		// 根据sid 修改 erp_order_detail 折后单价，订单数量，订单金额
		if (list != null && list.size()>0 ) {
			orderMapper.updateErpOrderDetail(list);
			Map<String,String> paramMap=new HashMap<String,String>();
			paramMap.put("sid", erpOrderDetailVo.getErpOrderSid());
	        paramMap.put("empInfo", erpOrderDetailVo.getEmpId()+"-"+erpOrderDetailVo.getEmpName());
	        paramMap.put("currentDate", currentDate);
	        orderMapper.updateErpOrderStatus(paramMap);
	        resultMap.put("currentDate", currentDate);
	        createOrderMapper.updateErpOrderSumAmount(erpOrderDetailVo.getErpOrderSid());
		}
		createOrderService.itemGift(erpOrderDetailVo);
		ErpOrderDto erpOrder=orderMapper.getErpOrderBySid(erpOrderDetailVo.getErpOrderSid());
		 if(null != erpOrder) {
			 erpOrder.setPurchaseOrederInfoDetailList(orderMapper.getErpOrderInfoDetails(erpOrder.getSid()));
		 }
		 resultMap.put("erpOrder", erpOrder);
		return resultMap;
	}
}