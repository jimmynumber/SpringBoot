package com.want.amap.repository;

import java.util.List;
import java.util.Map;

import com.want.amap.domain.GaodeMapPoiAddressAllAdWithThirdForthAllAd;
import com.want.amap.vo.BizUnit;
import com.want.amap.vo.ExcelVO;
import com.want.amap.vo.PoiListVO;


public interface GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository {
	public List<GaodeMapPoiAddressAllAdWithThirdForthAllAd> findById(String compId, String branchId);

	Map<String, List<List<ExcelVO>>> findExportExcel(List<BizUnit> allZones, List<PoiListVO> defaultPoiType,
			StringBuffer mailContent);
}
