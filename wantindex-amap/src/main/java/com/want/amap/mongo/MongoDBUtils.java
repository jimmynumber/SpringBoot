/**
 * -------------------------------------------------------
 * @FileName：MongoDBUtils.java
 * @Description：简要描述本文件的内容
 * @Author：Luke.Tsai
 * @Copyright  www.want-want.com  Ltd.  All rights reserved.
 * 注意：本内容仅限于旺旺集团内部传阅，禁止外泄以及用于其他商业目的
 * -------------------------------------------------------
 */
package com.want.amap.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.PoiListVO;
import com.want.amap.vo.PoiUnit;

public class MongoDBUtils {
	public final static Gson gson = new GsonBuilder().serializeNulls().create();
	public final static String DATABASE = PropertiesUtil.getMongoProperties().getProperty("dbName");
	public final static String COLLECTION_POI_INFO = 
			PropertiesUtil.getMongoProperties().getProperty("COLLECTION_POI_INFO");
	public final static String COLLECTION_POI_LIST = 
			PropertiesUtil.getMongoProperties().getProperty("COLLECTION_POI_LIST");
	public final static String uri = 
			"mongodb://" + PropertiesUtil.getMongoProperties().getProperty("account") + ":"
			+ PropertiesUtil.getMongoProperties().getProperty("passwd") + "@"
			+ PropertiesUtil.getMongoProperties().getProperty("dbHost") + ":27017/admin";
	public final static MongoClient mongoClient = new MongoClient(new MongoClientURI(uri));

	public static List<PoiUnit> listPois(String compId, String branchId) {
		Document q = new Document("companyID", compId);
		if (branchId != null) {
			q = q.append("branchID", branchId);
		}
		List<PoiUnit> pois = new ArrayList<PoiUnit>();
		for (Document cur : mongoClient.getDatabase(DATABASE).getCollection(COLLECTION_POI_INFO).find(q)
				.noCursorTimeout(true)) {
			PoiUnit poi = new PoiUnit(compId, branchId);
			String districtName = cur.getString("districtName");
			poi.setDistrictName(districtName);
			String compName = cur.getString("companyName");
			poi.setCompName(compName);
			String branchName = cur.getString("branchName");
			poi.setBranchName(branchName);
			String marketName = cur.getString("marketName");
			poi.setMarketName(marketName);
			String marketId = cur.getString("marketID");
			poi.setMarketId(marketId);
			String smallMarketId = cur.getString("smallMarketID");
			poi.setSmallMarketId(smallMarketId);
			String smallMarketName = cur.getString("smallMarketName");
			poi.setSmallMarketName(smallMarketName);
			String wantThirdId = cur.getString("wantThirdID");
			poi.setWantThirdId(wantThirdId);
			String wantThirdName = cur.getString("wantThirdName");
			poi.setWantThirdName(wantThirdName);
			String wantForthId = cur.getString("wantForthID");
			poi.setWantForthId(wantForthId);
			String wantForthName = cur.getString("wantForthName");
			poi.setWantForthName(wantForthName);

			@SuppressWarnings("unchecked")
			List<String> poiTypeList = (List<String>) cur.get("poiTypeList");
			if (poiTypeList.size() > 0) {
				poi.setPoiType1(poiTypeList.get(0));
			}
			if (poiTypeList.size() > 1) {
				poi.setPoiType2(poiTypeList.get(1));
			}
			if (poiTypeList.size() > 2) {
				poi.setPoiType3(poiTypeList.get(2));
			}

			String poiName = cur.getString("poiName");
			poi.setPoiName(poiName);
			String poiAddress = cur.getString("poiAddress");
			poi.setPoiAddress(poiAddress);
			String poiRealAddress = cur.getString("poiRealAddress");
			poi.setPoiRealAddress(poiRealAddress);
			String poiId = cur.getString("poiID");
			poi.setPoiId(poiId);
			String poiTel = cur.getString("poiTel");
			poi.setPoiTel(poiTel);
			pois.add(poi);
		}
		return pois;
	}

	public static List<PoiListVO> getPoiList() {
		List<PoiListVO> list = new ArrayList<>();
		for (Document document : mongoClient.getDatabase(DATABASE).getCollection(COLLECTION_POI_LIST).find()
				.noCursorTimeout(true)) {
			String json = com.mongodb.util.JSON.serialize(document);
			PoiListVO vo = (PoiListVO) MongoDBUtils.gson.fromJson(json, new TypeToken<PoiListVO>() {
			}.getType());
			list.add(vo);
		}

		return list;
	}

	public static List<PoiUnit> getPoiInfo() {
		List<PoiUnit> list = new ArrayList<>();
		for (Document document : mongoClient.getDatabase(DATABASE).getCollection(COLLECTION_POI_INFO).find()
				.noCursorTimeout(true)) {
			String json = com.mongodb.util.JSON.serialize(document);
			PoiUnit poi = (PoiUnit) MongoDBUtils.gson.fromJson(json, new TypeToken<PoiUnit>() {
			}.getType());
			list.add(poi);
		}

		return list;
	}

	public static void main(String[] args) {
		System.out.println(PropertiesUtil.getMongoProperties().getProperty("jdbc.driverClassName1"));
	}

}
