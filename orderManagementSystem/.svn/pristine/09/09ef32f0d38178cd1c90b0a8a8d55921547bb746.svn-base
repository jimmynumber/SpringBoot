package com.want.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.want.dto.ErpOrderDto;
import com.want.service.IResetOrderService;

@CrossOrigin
@RestController 
public class ResetOrderController {

	@Autowired
    private IResetOrderService service;
	
	@GetMapping("/getErpOrderReset") 
	public List<ErpOrderDto> getErpOrderReset(String erpOrderCode,String sid) { 
		return service.getErpOrder(erpOrderCode,sid);
	} 
	 	
	@GetMapping(value = "/resetErpOrder") 
    public Map<String, String> resetErpOrder(String sid,String userId,String userName,String reaetReason) {
        return service.updateErpOrder(sid,userId,userName,reaetReason);
    }  
	
}
