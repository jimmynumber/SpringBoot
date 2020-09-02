package com.want.mapper187;

import org.apache.ibatis.annotations.Mapper;
import com.want.vo.CustomerBusinessInfo;

@Mapper
public interface CustomerBusinessInfoMapper {
	void mergeCustomerBusinessInfo(CustomerBusinessInfo customerBusinessInfo);
}
