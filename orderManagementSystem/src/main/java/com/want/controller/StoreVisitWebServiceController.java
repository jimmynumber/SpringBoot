package com.want.controller;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.want.dto.OTReturn;
import com.want.po.Paging;
import com.want.service.IStoreVisitWebService;
import com.want.vo.StoreVisit;

@WebService
public class StoreVisitWebServiceController {
	
	 @Autowired
     private IStoreVisitWebService service;
	  
	  @PostMapping("/syncStoreVisit") 
	  public OTReturn syncStoreVisit(List<StoreVisit> storeVisit,Paging page){ 
		  System.out.println("syncStoreVisit--current:"+page.getCurrent()+";total:"+page.getTotal());
		  return service.syncStoreVisit(storeVisit,page);
	  }
}
