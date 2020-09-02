package com.want.amap.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.BizUnit;

public class SqlUtil {
	//prd
	static String host = PropertiesUtil.getMysqlProperties().getProperty("host");
	static String account = PropertiesUtil.getMysqlProperties().getProperty("account");
	static String passwd = PropertiesUtil.getMysqlProperties().getProperty("passwd");
	static String dbName = PropertiesUtil.getMysqlProperties().getProperty("dbName");
	
	//local test
//	static String host = "10.0.26.224";
//	static String account = "wwdevelop";
//	static String passwd = "5tgb^YHN";
//	static String dbName = "wantHD2";

	private static Connection connection;

	public static void init() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}

	static Connection getConnection() throws Exception {
		if (connection == null) {
			connection = DriverManager.getConnection("" + "jdbc:mysql://" + host + ":3306/" + dbName + "?user="
					+ account + "&password=" + passwd + "&useUnicode=yes&characterEncoding=utf8");
		}
		return connection;
	}

	public static void close() {
		if (connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (Exception e) {
			}
		}
	}

//	private static List<BizUnit> listBizUnits(String parentId, int level) throws Exception {
//		Connection con = getConnection();
//		String sql = "";
//		String selectString = null;
//		String whereString = null;
//		String groupByString = null;
//		String orderByString = null;
//		switch (level) {
//		case BizUnit.ZONE:
//			selectString = "district_id as id, district_name as name";
//			whereString = "";
//			groupByString = "district_id";
//			orderByString = "district_id";
//			break;
//		case BizUnit.COMPANY:
//			selectString = "district_id, company_id as id, company_name as name";
//			whereString = "";
//			groupByString = "company_id";
//			orderByString = "district_id, company_id";
//			break;
//		case BizUnit.BRANCH:
//			selectString = "company_id, branch_id as id, branch_name as name";
//			whereString = " and company_id=? and branch_id is not null and branch_id <> ''";
//			groupByString = "branch_id";
//			orderByString = "branch_id";
//			break;
//		case BizUnit.MARKET:
//			selectString = "branch_id, market_id as id, market_name as name";
//			whereString = " and branch_id=? and market_id is not null and market_id <> ''";
//			groupByString = "market_id";
//			orderByString = "market_id";
//			break;
//		case BizUnit.SMALL_MARKET:
//			selectString = "market_id, small_market_id as id, small_market_name as name";
//			whereString = " and market_id=? and small_market_id is not null and small_market_id <> ''";
//			groupByString = "small_market_id";
//			orderByString = "small_market_id";
//			break;
//		}
//		
//		sql = "SELECT " + selectString + " FROM wantHD2.data_division_to_market " + 
//				"WHERE district_id LIKE 'Y%'" + whereString + 
//				" group by " + groupByString + 
//				" order by " + orderByString;
//		
//		PreparedStatement stmt = con.prepareStatement(sql);
//		System.out.println("parentId = " + parentId);
//		if (parentId != null && !parentId.trim().equals("")) {
//			stmt.setString(1, parentId);
//		}
//		ResultSet rs = stmt.executeQuery();
//		List<BizUnit> units = new ArrayList<>();
//		while (rs.next()) {
//			String name = rs.getString("name");
//			String id = rs.getString("id");
//			BizUnit comp = new BizUnit(level, id, name);
//			comp.setParentId(parentId);
//			units.add(comp);
//		}
//		rs.close();
//		stmt.close();
//		return units;
//	}

//	public static List<BizUnit> listZones() throws Exception {
//		return SqlUtil.listBizUnits(null, BizUnit.ZONE);
//	}

//	public static List<BizUnit> listCompanies(String zoneId) throws Exception {
//		return SqlUtil.listBizUnits(zoneId, BizUnit.COMPANY);
//	}

//	private static void findBranch(BizUnit comp) throws Exception {
//		List<BizUnit> brs = new ArrayList<BizUnit>();
//		List<BizUnit> comp2s = SqlUtil.listBizUnits(comp.getId(), BizUnit.BRANCH);
//		for (BizUnit comp2 : comp2s) {
//			System.out.println(comp.getName() + ":" + comp2.getName());
//			comp2.setParentId(comp.getId());
//			brs.add(comp2);
//		}
//		comp.setChilds(brs);
//	}

//	public static void findMarket(BizUnit branch) throws Exception {
//		List<BizUnit> markets = SqlUtil.listBizUnits(branch.getId(), BizUnit.MARKET);
//		branch.setChilds(markets);
//	}

//	private static void findSmallMarket(BizUnit market) throws Exception {
//		List<BizUnit> smallMarkets = SqlUtil.listBizUnits(market.getId(), BizUnit.SMALL_MARKET);
//		market.setChilds(smallMarkets);
//	}

//	public static List<BizUnit> listAll() throws Exception {
//		List<BizUnit> comps = SqlUtil.listBizUnits(null, BizUnit.COMPANY);
//		System.out.println(comps);
//		for (BizUnit comp : comps) {
//			System.out.println(comp.getName());
//			SqlUtil.findBranch(comp);
//			for (BizUnit br : comp.getChilds()) {
//				System.out.println(">>" + br.getName());
//				SqlUtil.findMarket(br);
//				for (BizUnit market : br.getChilds()) {
//					System.out.println(">>>>" + market.getName());
//					SqlUtil.findSmallMarket(market);
//					for (BizUnit smallMarket : market.getChilds()) {
//						System.out.println(">>>>>>" + smallMarket.getName());
//					}
//				}
//			}
//		}
//		return comps;
//	}
}
