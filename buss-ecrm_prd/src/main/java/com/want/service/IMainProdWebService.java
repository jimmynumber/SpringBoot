package com.want.service;

import java.util.Collection;
import com.want.dto.MainProd;

public interface IMainProdWebService {
	//获取旺旺产品接口
	Collection<MainProd> getMainProd(String startDate,String endDate);
	
}
