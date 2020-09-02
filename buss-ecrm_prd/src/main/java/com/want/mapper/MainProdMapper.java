package com.want.mapper;

import java.util.Collection;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import com.want.dto.MainProd;
@Mapper
public interface MainProdMapper {
	//获取旺旺产品接口
	Collection<MainProd> getMainProd(Map<String,String> map);
}
