package com.want.amap.mongo;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.ansj.lucene7.AnsjAnalyzer;
import org.ansj.lucene7.AnsjAnalyzer.TYPE;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.want.amap.oracle.OracUtil;
import com.want.amap.sql.SearchUnitIndex;
import com.want.amap.sql.SqlToIndex;
import com.want.amap.sql.SqlUtil;
import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.BizUnit;
import com.want.amap.vo.PoiListQueryStringVO;
import com.want.amap.vo.PoiUnit;

public class MongoToIndex {
	private static Analyzer analyzer;
	private static Directory dir;
	private static IndexWriter iw;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static String COMMAND_EXPORTEXCEL = "exportExcel";

//	public static void main(String[] args) {
//		try {
//			String start = sdf.format(new Date());
//			SqlUtil.init();
//			System.out.println("SQL inited!");
//			MongoUtils.init(); // 連線MongoDB
//			List<PoiListQueryStringVO> defaultPoiTypes = null;
//			if (args != null && args.length > 0 && args[0].equals(COMMAND_EXPORTEXCEL)) {//只匯出excel
//				long time = System.currentTimeMillis();
//				OracUtil.getConn();
//				defaultPoiTypes = OracUtil.getDefaultPoiType();
//				OracUtil.close();
//				List<BizUnit> zones = SqlUtil.listZones();
//				SqlUtil.close();
//				MongoUtils.exportPoiTypeGroupCount(zones, defaultPoiTypes);
//				System.out.println("Export excel is finished and spend " + (System.currentTimeMillis() - time) / 1000 + " seconds");
//			} else {
//				// 制作分公司索引
//				List<BizUnit> units = SqlUtil.listAll();
//				List<BizUnit> zones = SqlUtil.listZones();
//				System.out.println("Load Data Done! " + units.size() + " companies!");
//				SqlToIndex.init();
//				System.out.println("Lucene inited!");
//				SqlToIndex.addDocs(units);
//				System.out.println("Index Created!");
//				SqlUtil.close();
//
//				// 制作终端索引
//				SearchUnitIndex.init();
//				init();
//				List<BizUnit> comps = SearchUnitIndex.getCompanyIds();
//				int total = addToIndex(comps);
//				String end = sdf.format(new Date());
//
//				if (total > 0) {
//					// 查询预设设施类别总笔数
//					SearchPoiIndex.init();
//					OracUtil.getConn();
//					defaultPoiTypes = OracUtil.getDefaultPoiType();
//					OracUtil.close();
//					Map<String, Integer> map = SearchPoiIndex.countByDefaultPoiType(defaultPoiTypes);
//
//					// 时间、笔数写入mongo
//					org.bson.Document logDoc = new org.bson.Document();
//					logDoc.append("indexNum", map.get("indexNum"));
//					logDoc.append("validNum", map.get("validNum"));
//					logDoc.append("defaultTypeNum", map.get("defaultTypeNum"));
//					logDoc.append("start", start);
//					logDoc.append("end", end);
//					Calendar ca = Calendar.getInstance();
//					if (ca.get(Calendar.DAY_OF_MONTH) > 5) {
//						ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) + 1);
//					}
//					ca.set(Calendar.DAY_OF_MONTH, 5);
//					logDoc.append("update", new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime()));
//
//					// 取出上一笔Log数据
//					org.bson.Document doc = MongoUtils.getLastIndexLog();
//					System.out.println("index = " + (int) doc.get("indexNum"));
//					System.out.println("valid = " + (int) doc.get("validNum"));
//					System.out.println("defaultTypeNum = " + (int) doc.get("defaultTypeNum"));
//
//					// 计算与上期差异
//					logDoc.append("indexDiff", (map.get("indexNum") - (int) doc.get("indexNum")));
//					logDoc.append("validDiff", (map.get("validNum") - (int) doc.get("validNum")));
//					logDoc.append("defaultTypeDiff", (map.get("defaultTypeNum") - (int) doc.get("defaultTypeNum")));
//
//					MongoUtils.getDB().getCollection(MongoUtils.colName2).insertOne(logDoc);
//					System.out.println("Insert mongo success");
//
//					//製作客戶索取數據
//					MongoUtils.exportPoiTypeGroupCount(zones, defaultPoiTypes);
//				}
//			}
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			OracUtil.close();
//			MongoUtils.close();
//			SearchUnitIndex.close();
//			SqlUtil.close();
//		}
//	}

	static int addToIndex(List<BizUnit> comps) {
		int count = 1;
		long t1 = System.currentTimeMillis();
		int poiCount = 0;

		try {
			for (BizUnit comp : comps) {
				String compId = comp.getId();
				System.out.println("Adding " + compId + " (" + count + "/" + comps.size() + ")");
				count++;
				List<BizUnit> branches;

				branches = SearchUnitIndex.getChilds(compId);

				for (BizUnit branch : branches) {
					System.out.println(" Adding " + branch.getName() + "...");
					// 取得數據來源
					List<PoiUnit> pois = MongoUtils.listPois(compId, branch.getId());

					// write doc
					for (PoiUnit poi : pois) {
						Document doc = new Document();
						doc.add(new StringField("companyId", poi.getCompId(), Field.Store.NO));
						doc.add(new StringField("branchId", poi.getBranchId(), Field.Store.YES));
						doc.add(new StringField("marketId", poi.getMarketId(), Field.Store.YES));
						doc.add(new StringField("smallMarketId", poi.getSmallMarketId(), Field.Store.YES));
						doc.add(new StringField("wantThirdId", poi.getWantThirdId(), Field.Store.NO));
						doc.add(new StringField("wantForthId", poi.getWantForthId(), Field.Store.YES));
						doc.add(new StringField("towncode", poi.getTowncode(), Field.Store.YES));

						doc.add(new StringField("districtName", poi.getDistrictName(), Field.Store.YES));
						doc.add(new StringField("compName", poi.getCompName(), Field.Store.YES));
						doc.add(new StringField("branchName", poi.getBranchName(), Field.Store.YES));
						doc.add(new StringField("marketName", poi.getMarketName(), Field.Store.YES));
						doc.add(new StringField("smallMarketName", poi.getSmallMarketName(), Field.Store.YES));
						doc.add(new StringField("wantThirdName", poi.getWantThirdName(), Field.Store.NO));
						doc.add(new StringField("wantForthName", poi.getWantForthName(), Field.Store.YES));
						doc.add(new StringField("township", poi.getTownship(), Field.Store.YES));

						//20181001  mongo db 的poiTypeList結構改變，查詢改為模糊查詢
//						doc.add(new StringField("poiType1", poi.getPoiType1(), Field.Store.YES));
//						doc.add(new StringField("poiType2", poi.getPoiType2(), Field.Store.YES));
//						doc.add(new StringField("poiType3", poi.getPoiType3(), Field.Store.YES));
						doc.add(new Field("poiType1", poi.getPoiType1(), new FieldType(TextField.TYPE_STORED)));
						doc.add(new Field("poiType2", poi.getPoiType2(), new FieldType(TextField.TYPE_STORED)));
						doc.add(new Field("poiType3", poi.getPoiType3(), new FieldType(TextField.TYPE_STORED)));

						doc.add(new Field("poiTypeListString", poi.getPoiTypeListString(),
								new FieldType(TextField.TYPE_STORED)));
						doc.add(new Field("poiName", poi.getPoiName(), new FieldType(TextField.TYPE_STORED)));
						doc.add(new TextField("poiAddress", poi.getPoiAddress(), Field.Store.YES));
						doc.add(new Field("poiRealAddress", poi.getPoiRealAddress(),
								new FieldType(TextField.TYPE_STORED)));
						doc.add(new StringField("poiId", poi.getPoiId(), Field.Store.YES));
						doc.add(new StringField("poiTel", poi.getPoiTel(), Field.Store.YES));
						doc.add(new StringField("poiCategory", poi.getPoiCategory(), Field.Store.YES));
						doc.add(new StringField("longitude", poi.getLongitude(), Field.Store.YES));
						doc.add(new StringField("latitude", poi.getLatitude(), Field.Store.YES));
						iw.addDocument(doc);
						poiCount++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			poiCount = -1;
		} finally {
			if (iw != null) {
				try {
					iw.forceMergeDeletes();
					iw.commit();
					iw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (dir != null) {
				try {
					dir.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("\n\n总數量 = " + poiCount + "\n\n");
		long t2 = System.currentTimeMillis();
		System.out.println("Took " + (t2 - t1) + " mSeconds for " + poiCount + " poi data!");
		
		return poiCount;
	}

	public static void init() throws Exception {
		analyzer = new AnsjAnalyzer(TYPE.index_ansj);
		dir = FSDirectory.open(Paths.get(PropertiesUtil.getMongoProperties().getProperty("path")));
//		dir = FSDirectory.open(Paths.get("/Users/80005121/Documents/img/lucene_index/poi_index_mapping"));
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		iw = new IndexWriter(dir, iwc);
	}

}
