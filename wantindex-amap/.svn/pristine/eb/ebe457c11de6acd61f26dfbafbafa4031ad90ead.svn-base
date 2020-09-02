package com.want.amap.mapper.mysql;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.want.amap.vo.BizUnit;

public interface DataDivisionToMarketMapper {
	@Select(
			"SELECT district_id as id, district_name as name "
			+ "FROM wantHD2.data_division_to_market "
			+ "WHERE district_id LIKE 'Y%' "
			+ "group by district_id "
			+ "order by district_id;")
	public List<BizUnit> getAllZones();

	@Select(
			"SELECT district_id, company_id as id, company_name as name "
			+ "FROM wantHD2.data_division_to_market "
			+ "WHERE district_id LIKE 'Y%' "
			+ "group by company_id "
			+ "order by district_id,company_id;")
	public List<BizUnit> getAllCompany();
	
	@Select(
			"SELECT company_id, branch_id as id, branch_name as name "
			+ "FROM wantHD2.data_division_to_market "
			+ "WHERE district_id LIKE 'Y%' and company_id=#{id} "
			+ "group by branch_id "
			+ "order by branch_id;")
	public List<BizUnit> getAllBranch(BizUnit unit);

	@Select(
			"SELECT branch_id, market_id as id, market_name as name "
			+ "FROM wantHD2.data_division_to_market "
			+ "WHERE district_id LIKE 'Y%' and branch_id=#{id} "
			+ "group by market_id "
			+ "order by market_id;")
	public List<BizUnit> getAllMarket(BizUnit unit);
	
	@Select(
			"SELECT market_id, small_market_id as id, small_market_name as name "
			+ "FROM wantHD2.data_division_to_market "
			+ "WHERE district_id LIKE 'Y%' and market_id=#{id} "
			+ "group by small_market_id "
			+ "order by small_market_id;")
	public List<BizUnit> getAllSmallMarket(BizUnit unit);
}
