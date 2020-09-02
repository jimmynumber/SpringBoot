package com.want.service;

import java.util.List;
import java.util.Map;

import com.want.dto.ErpOrderDto;

public interface IResetOrderService {

    List<ErpOrderDto> getErpOrder(String erpOrderCode,String sid);
    
    Map<String, String> updateErpOrder(String sid,String userId,String userName,String reaetReason);
    
}
