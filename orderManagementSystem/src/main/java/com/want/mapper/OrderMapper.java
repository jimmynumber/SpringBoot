package com.want.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.want.dto.AttributeInfo;
import com.want.dto.ErpOrderDetailDto;
import com.want.dto.ErpOrderDto;
import com.want.dto.OrderDetailDto;
import com.want.dto.OrderDto;
import com.want.dto.OrderInfoDto;
import com.want.dto.Organization;
import com.want.po.ERPOrder;
import com.want.po.ERPOrderDetail;
import com.want.po.OrderDetail;
import com.want.po.OrderInfo;
import com.want.po.PackageInfo;
import com.want.po.ProductAssign;
import com.want.po.ProductInfo;
import com.want.vo.Order;

@Mapper
public interface OrderMapper { 
	
	public List<OrderInfo> orderList();
	
	public void insertOrderInfo(@Param("order")OrderInfo order); 
	
	public void insertOrderDetail(List<OrderDetail> list); 
	
	public List<Organization> orgList(@Param("empId")String empId);
	
	public long queryOrderByCode(@Param("orderCode")String orderCode);
	
	public long queryERPOrderByCode(@Param("orderId")String orderId);
	
	void updateOrderStatusByOrderId(@Param("updateUser")String updateUser,@Param("orderId")String orderId,@Param("cancelReason")String cancelReason);
	
	void updateERPOrderStatusByOrderId(@Param("updateUser")String updateUser,@Param("orderId")String orderId,@Param("cancelReason")String cancelReason);
	
	public List<ProductInfo> queryUnitByProductCode(@Param("productCode")String productCode);
	
	public List<PackageInfo> prodList(@Param("packageId")String packageId);
	
	public List<PackageInfo> packageList();
	
	public long insertERPOrder(List<ERPOrder> list);
	
	public void insertERPOrderDetail(List<ERPOrderDetail> list);
	
	public List<ProductAssign> getProductAssignByCode(@Param("productCode")String productCode,@Param("customerCode")String customerCode);
	
	// 根据 客户编号，订单编号，下单时间 获取订单信息 
	List<OrderInfoDto> queryOrderByCondition(Order order);
	
	// 根据 订单编号,获取SOCS需求订单信息
	OrderDto getOrderInfos(@Param("orderCode")String orderCode); 
	
	// 根据 订单编号,获取SOCS需求订单详情
	List<OrderDetailDto> getOrderInfoDetails(@Param("orderCode")String orderCode); 
	
	// 根据 订单编号,获取采购订单信息
    List<ErpOrderDto> getErpOrders(@Param("orderCode")String orderCode);
    
    // 根据 订单编号,获取采购订单信息
    ErpOrderDto getErpOrderBySid(@Param("sid")String sid);
    
    // 根据 订单编号,获取采购订单详情 
    List<ErpOrderDetailDto> getErpOrderInfoDetails(@Param("erpOrderSid")String erpOrderSid);
    
    // 将erp 返回 erp订单编码，状态，错误原因根据订单编码更新
    void updateErpOrder(Map<String,String> map);
    
    // 将erp 返回 erp订单详情编码，状态，错误原因根据订单明细编码更新
    void updateErpOrderDetail(List<Map<String, String>> list);
    
    // 根据sid 查询 erp_order订单状态
    Map<String,String> checkErpOrderStatus(@Param("sid")String sid);
    
    // 更新ERP_ORDER表的订单状态 
    void updateErpOrderStatus(Map<String,String> map);

    // 根据orderCode 查询 erp_order订单状态
    Map<String,String> checkErpOrderAllStatus(@Param("orderCode")String orderCode);
    
    // 更新ORDER_INFO表的订单状态 
    void updateOrderInfoStatus(Map<String,String> map);
    
    // 获取配置属性
    List<AttributeInfo> getAttributeInfo(Map<String,String> map);
    
    // 更新CUSTOMER_REBATE表的订单状态 
    void updateCustomerRebateStatus(Map<String,String> map);
    
    //新增返利流水
    void insertCustomerRebateRecord(@Param("customerCode")String customerCode,@Param("updateUser")String updateUser,@Param("erpOrderSid")String erpOrderSid,@Param("rebateAmount")String rebateAmount);
    
    String validateCustomerRebateStatus(@Param("customerCode")String customerCode);
    
    String validateRemainRebateAmount(@Param("customerCode")String customerCode);
    
    void updateOrderRebateAmount(@Param("sid")String sid,@Param("amount")String amount);
    
} 