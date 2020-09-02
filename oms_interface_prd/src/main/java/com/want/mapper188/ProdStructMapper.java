package com.want.mapper188;

import org.apache.ibatis.annotations.Mapper;
import com.want.vo.ProdStruct;

@Mapper
public interface ProdStructMapper {
	// 新增或修改产品结构
	void mergeProdStruct(ProdStruct prodStruct);

	// 删除前一天数据
	void deleteProdStruct();
}
