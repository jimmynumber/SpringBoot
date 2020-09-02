package com.want.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.want.dto.MainProd;
import com.want.mapper.MainProdMapper;
import com.want.service.IMainProdWebService;
import com.want.util.ExceptionUtil;
/**
 * 怡海主动调用旺旺产品接口
 * @author 00320558
 * 2019-12-10 10:20:00 
 */
@Service
//@Transactional
public class MainProdWebServiceImpl implements IMainProdWebService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MainProdWebServiceImpl.class);
	@Autowired
    private MainProdMapper mapper;
	
	@Override
	public Collection<MainProd> getMainProd(String startDate, String endDate) {
		LOGGER.info("--MainProdWebServiceImpl.getMainProd,startDate:{},endDate:{} ",startDate,endDate);
		//【开始日期】与【结束日期】两个字段必须联合使用。
		//1、当两个字段都为空值时，输出全量数据；
		//2、当两个字段都为日期时，按日期范围限定输出【更新日期】符合条件的数据；
		//3、其他情况不返回值。"
		Map<String,String> paramMap=new HashMap<String, String>();
        if ( (startDate ==null || startDate.trim().length()<=0 )  && (endDate ==null || endDate.trim().length()<=0 ) ) {
        	return mapper.getMainProd(paramMap);
		}else {
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				if(dateFormat.parse(startDate).getTime() > dateFormat.parse(endDate).getTime()){
					paramMap.put("startDate",endDate );
					paramMap.put("endDate",startDate);
				}
			} catch (ParseException e) {
				LOGGER.error("--MainProdWebServiceImpl.getMainProd,startDate:{},endDate:{},e:{} ",startDate,endDate,ExceptionUtil.getTrace(e)+"");
				return null;
			}
			return mapper.getMainProd(paramMap);
		}
	}
}
