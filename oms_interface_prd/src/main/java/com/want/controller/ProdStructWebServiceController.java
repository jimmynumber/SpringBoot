package com.want.controller;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.want.response.ResponseEntity;
import com.want.service.IProdStructWebService;
import com.want.vo.ProdStruct;

@WebService
public class ProdStructWebServiceController {

	@Autowired
    private IProdStructWebService Service;
	  
	@PostMapping("/syncProdStruct")
	public ResponseEntity syncProdStruct (List<ProdStruct> prodStructList) {
		return Service.syncProdStruct(prodStructList);
	}
	
}
