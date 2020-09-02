package com.want.controller;

import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import com.want.dto.OT_RETURN;
import com.want.service.IOrderWebService;
import com.want.vo.IT_EKKO;
import com.want.vo.IT_EKPO;

@WebService
public class OrderWebServiceController {

	@Autowired
    private IOrderWebService service;
	
	@GetMapping("/syncOrder") 
	public OT_RETURN syncOrder(IT_EKKO order,List<IT_EKPO> orderDetail) { 
		return service.syncOrder(order, orderDetail);
	}
}
