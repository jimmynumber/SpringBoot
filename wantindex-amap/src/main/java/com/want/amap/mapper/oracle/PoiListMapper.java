package com.want.amap.mapper.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.want.amap.vo.PoiListVO;

public interface PoiListMapper {
	
//	@Select("SELECT LV1, LV2, LV3, ('.*'||REPLACE(REPLACE(REPLACE(LV3, ')', '\\)'), '(', '\\('), '/', '\\/')||'.*') AS queryString FROM POI_LIST")
	@Select("SELECT * FROM POI_LIST")
	public List<PoiListVO> getDefaultPoiType();
}
