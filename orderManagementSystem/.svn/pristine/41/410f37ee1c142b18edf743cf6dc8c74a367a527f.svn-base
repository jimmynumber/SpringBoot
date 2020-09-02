package com.want.controller;

import java.util.List;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import com.want.dto.OTReturn;
import com.want.po.Paging;
import com.want.service.IStoreVisitTargetWebService;
import com.want.vo.StoreVisitTarget;

@WebService
public class StoreVisitTargetWebServiceController {
	
	 @Autowired
    private IStoreVisitTargetWebService service;
    
	  @PostMapping("/syncStoreVisitTarget") 
	  public OTReturn syncStoreVisitTarget(List<StoreVisitTarget> storeVisitTarget,Paging page){ 
		  System.out.println("syncStoreVisitTarget--current:"+page.getCurrent()+";total:"+page.getTotal());
		  return service.syncStoreVisitTarget(storeVisitTarget,page);
	  }
}
