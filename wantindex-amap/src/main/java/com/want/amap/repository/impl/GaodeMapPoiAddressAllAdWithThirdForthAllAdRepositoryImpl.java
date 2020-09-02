package com.want.amap.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.want.amap.domain.GaodeMapPoiAddressAllAdWithThirdForthAllAd;
import com.want.amap.repository.GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository;
import com.want.amap.util.CollectionUtil;
import com.want.amap.vo.BizUnit;
import com.want.amap.vo.ExcelVO;
import com.want.amap.vo.PoiListVO;

@Repository("GaodeMapPoiAddressAllAdWithThirdForth_AllAd")
public class GaodeMapPoiAddressAllAdWithThirdForthAllAdRepositoryImpl
		implements GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository {
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Map<String, List<List<ExcelVO>>> findExportExcel(List<BizUnit> allZones, List<PoiListVO> defaultPoiType, StringBuffer mailContent) {
		List<Pattern> patternList = new ArrayList<>();
		Map<String, List<List<ExcelVO>>> map = new HashMap<>();

		try {
			for (PoiListVO vo : defaultPoiType) {
				patternList.add(Pattern.compile(vo.getQueryString()));
			}

			for (BizUnit zone : allZones) {
				List<ExcelVO> mapList = new ArrayList<>();
				long begin = System.currentTimeMillis();
				Document match = new Document("$match", new Document("districtID", zone.getId()).append("poiTypeList.2",
						new Document("$in", patternList)));
				Document unwind = new Document("$unwind", "$poiTypeGroups");
				Document group = new Document("$group",
						new Document("_id",
								new Document("districtID", "$districtID").append("districtName", "$districtName")
										.append("companyID", "$companyID").append("companyName", "$companyName")
										.append("branchID", "$branchID").append("branchName", "$branchName")
										.append("marketID", "$marketID").append("marketName", "$marketName")
										.append("poiTypeGroups", "$poiTypeGroups")).append("count",
												new Document("$sum", 1)));
				Document sort = new Document("$sort", new Document("marketID", 1.0).append("poiTypeGroups.2", 1.0));
				AggregationResults<ExcelVO> results = mongoOperations
						.aggregate(Aggregation.newAggregation(Arrays.asList(new AggregationOperation() {

							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return context.getMappedObject(new BasicDBObject(match));
							}

						}, new AggregationOperation() {

							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return context.getMappedObject(new BasicDBObject(unwind));
							}

						}, new AggregationOperation() {

							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return context.getMappedObject(new BasicDBObject(group));
							}

						}, new AggregationOperation() {

							@Override
							public DBObject toDBObject(AggregationOperationContext context) {
								return context.getMappedObject(new BasicDBObject(sort));
							}

						})).withOptions(Aggregation.newAggregationOptions().allowDiskUse(true).build()),
								"GaodeMapPoiAddressAllAdWithThirdForth_AllAd", ExcelVO.class);

				List<ExcelVO> list = CollectionUtil.convertArrayList(results.getMappedResults());

				// 依標準市場編碼及設施小類排序
				Collections.sort(list, new Comparator<ExcelVO>() {
					public int compare(ExcelVO o1, ExcelVO o2) {
						int i = o1.getMarketID().compareTo(o2.getMarketID());
						int j = o1.getPoiTypeGroups().get(0).compareTo(o2.getPoiTypeGroups().get(0));
						int k = o1.getPoiTypeGroups().get(1).compareTo(o2.getPoiTypeGroups().get(1));
						if (i == 0) {
							if (j == 0) {
								if (k == 0) {
									return o1.getPoiTypeGroups().get(2).compareTo(o2.getPoiTypeGroups().get(2));
								} else {
									return k;
								}
							} else {
								return j;
							}
						} else {
							return i;
						}
					}
				});

				System.out.println("Find " + zone.getName() + "(" + (allZones.indexOf(zone) + 1) + "/" + allZones.size() + ")" + " " + list.size() + " data spend "
						+ (System.currentTimeMillis() - begin) / 1000 + " seconds");
				mailContent.append("<br>");
				mailContent.append("Find " + zone.getName() + "(" + (allZones.indexOf(zone) + 1) + "/" + allZones.size() + ")" + " " + list.size() + " data spend "
						+ (System.currentTimeMillis() - begin) / 1000 + " seconds");

				if (list.size() > 0) {// 若該大區有數據
					mapList.addAll(list);
					map.put(zone.getName(), Collections.singletonList(mapList));
					list.clear();
				} else {
					System.out.println(zone.getName() + " 查無數據 ！");
					mailContent.append("<br>");
					mailContent.append(zone.getName() + " 查無數據 ！");
					map.put(zone.getName(), null);
				}
				
				Thread.sleep(200);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public List<GaodeMapPoiAddressAllAdWithThirdForthAllAd> findById(String compId, String branchId) {
		Query query = new Query(new Criteria("companyID").is(compId).and("branchID").is(branchId));
		return mongoTemplate.find(query, GaodeMapPoiAddressAllAdWithThirdForthAllAd.class);
	}

}
