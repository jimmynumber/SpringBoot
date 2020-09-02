package com.want.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.want.vo.StoreVisitTarget;
import com.want.vo.StoreVisit;
import com.want.po.StoreVisitTagTime;

@Mapper
public interface VisitTractingMapper { 
	
	public void deleteStoreVisitTarget(@Param("dataTime")String dataTime);
	
	public void insertStoreVisitTarget(List<StoreVisitTarget> storeVisitTarget);
	
	public void deleteStoreVisit(@Param("dataTime")String dataTime);
	
	public void insertStoreVisit(List<StoreVisit> storeVisit);
	
	public void insertTagTime(List<StoreVisitTagTime> StoreVisitTagTime);
	
	public void clearTagTime(@Param("dataTime")String dataTime);
	
	void truncateStoreVisitTmp();
	
	void truncateTagTimeTmp();
	
	void mergerStoreVisit();
	
	void mergerTagTime();
	
	void insertStoreVisitTmp(List<StoreVisit> storeVisit);
	
	void insertTagTimeTmp(List<StoreVisitTagTime> StoreVisitTagTime);
	
	
	
	
} 