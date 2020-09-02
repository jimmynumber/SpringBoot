package com.want.controller;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.want.dto.ResponseEntity;
import com.want.service.IAttenceWebService;
import com.want.vo.Attence;

@WebService
public class AttenceWebServiceController {

      @Autowired
      private IAttenceWebService Service;
      
	  @PostMapping("/syncAttence") 
	  public ResponseEntity syncAttence(List<Attence> list){ 
		  return Service.accessAttence(list);
	  }
}
