package com.want.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.want.dto.MainProd;
import com.want.response.MainProdVo;
import com.want.service.IMainProdWebService;
/**
 * 怡海主动调用旺旺产品接口
 * @author 00320558
 * 2019-12-10 10:20:00 
 */
@WebService
public class MainProdWebServiceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainProdWebServiceController.class);

    @Autowired
    private IMainProdWebService Service;
	
    @GetMapping("/getMainProd") 
	public Collection<MainProdVo> getMainProd(String startDate,String endDate){ 
		LOGGER.info("--MainProdWebServiceController.getMainProd,startDate:{},endDate:{} ",startDate,endDate);
		 Collection<MainProd> list=Service.getMainProd(startDate, endDate);
		 return convertToMainProdVo(list);
	  }
    
    private Collection<MainProdVo> convertToMainProdVo(Collection<MainProd> list) {
    	if (list ==null || list.size()<=0) {
			return null;
		}
    	List<MainProdVo> mainProdVoList = new ArrayList<>();
		 list.stream().forEach(it ->{  
			 MainProdVo obj=new MainProdVo();
			 obj.setPRODUCT_CODE(nullTo(it.getProductCode()));
			 obj.setPRODUCT_NAME(nullTo(it.getProductName()));
			 obj.setPRD_SHORT_NAME(nullTo(it.getPrdShortName()));
			 obj.setBASE_UNIT(nullTo(it.getBaseUnit()));
			 obj.setBASE_UNIT_NAME(nullTo(it.getBaseUnitName()));
			 obj.setBASE_UNIT_WEIGHT(nullTo(it.getBaseUnitWeight()));
			 obj.setTAX(nullTo(it.getTax()));
			 obj.setSTORAGE(nullTo(it.getStorage()));
			 obj.setOUTER_CODE(nullTo(it.getOuterCode()));
			 obj.setPRICE(nullTo(it.getPrice()));
			 obj.setSTATUS(nullTo(it.getStatus()));
			 obj.setIMP(nullTo(it.getImp()));
			 obj.setUNIT(nullTo(it.getUnit()));
			 obj.setUNIT_NAME(nullTo(it.getUnitName()));
			 obj.setDENOMINATOR(nullTo(it.getDenominator()));
			 obj.setNUMERATOR(nullTo(it.getNumerator()));
			 obj.setSIMPLE_CODE(nullTo(it.getSimpleCode()));
			 obj.setUNIT_WEIGHT(nullTo(it.getUnitWeight()));
			 obj.setLV1_ID(nullTo(it.getLv1Id()));
			 obj.setLV1_NAME(nullTo(it.getLv1Name()));
			 obj.setLV2_ID(nullTo(it.getLv2Id()));
			 obj.setLV2_NAME(nullTo(it.getLv2Name()));
			 obj.setLV3_ID(nullTo(it.getLv3Id()));
			 obj.setLV3_NAME(nullTo(it.getLv3Name()));
			 obj.setLV4_ID(nullTo(it.getLv4Id()));
			 obj.setLV4_NAME(nullTo(it.getLv4Name()));
			 obj.setLV5_ID(nullTo(it.getLv5Id()));
			 obj.setLV5_NAME(nullTo(it.getLv5Name()));
			 obj.setUPDATE_DATE(nullTo(it.getUpdateDate()));
			 mainProdVoList.add(obj);
	        });
		 return mainProdVoList;
    }
    private String nullTo(String str) {
    	if(str == null ) {
    		return " ";
    	}
    	return str;
    }
}
