package com.want.wantworld.controller;

import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import com.want.wantworld.service.ILoginService;

@WebService
public class ValidateTokenWebServiceController {

    @Autowired
    private ILoginService service;
    
    @PostMapping("/validateToken")
    public Map<String, String> validateToken(String token ) { 
        return service.validateToken(token);
    }
}
