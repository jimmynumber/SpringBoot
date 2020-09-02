package com.want.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.want.dto.ErpOrderDetailDto;
import com.want.dto.ErpOrderDto;
import com.want.po.OrderReset;

@Mapper
public interface ResetOrderMapper { 
	
    List<ErpOrderDto> getErpOrder(@Param("erpOrderCode")String erpOrderCode,@Param("sid")String sid);
    
    ErpOrderDto getErpOrderBySid(@Param("sid")String sid);
    
    List<ErpOrderDetailDto> getErpOrderInfoDetails(@Param("erpOrderSid")String erpOrderSid);
    
    void updateErpOrder(Map<String,String> map);
    
    void updateOrder(Map<String,String> map);
    
    void insertOrderReset(@Param("orderReset")OrderReset orderReset); 
    
    void updateErpOrderDetail(@Param("sid")String sid); 
    
    void updateCustomerRebate(@Param("sid")String sid); 
    
    String validateCustomerRebate(@Param("sid")String sid); 
    
    void insertCustomerRebateRecord(@Param("sid")String sid,@Param("createUser")String createUser); 
    
} 