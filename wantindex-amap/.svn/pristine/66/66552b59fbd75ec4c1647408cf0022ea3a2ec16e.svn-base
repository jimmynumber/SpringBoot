package com.want.amap.mongo;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.BizUnit;
import com.want.amap.vo.PoiListQueryStringVO;
import com.want.amap.vo.PoiUnit;

public class MongoUtils {
	static int SHEET_LIMIT = 65000;
	static String dbHost = PropertiesUtil.getMongoProperties().getProperty("dbHost");
	static String account = PropertiesUtil.getMongoProperties().getProperty("account");
	static String passwd = PropertiesUtil.getMongoProperties().getProperty("passwd");
	static String dbName = PropertiesUtil.getMongoProperties().getProperty("dbName");

	static String colName = PropertiesUtil.getMongoProperties().getProperty("colName");
	static String colName1 = PropertiesUtil.getMongoProperties().getProperty("colName1");
	static String colName2 = PropertiesUtil.getMongoProperties().getProperty("colName2");
//	static String dbHost = "10.0.26.164";
//	static String account = "wwadmin";
//	static String passwd = "0ozj6DAj52";
//	static String dbName = "spider";
//
//	static String colName = "GaodeMapPoiAddressAllAdWithThirdForth_AllAd";
//	static String colName1 = "AllPoiInfo";
//	static String colName2 = "IndexLog";

	private static boolean inited = false;
	private static MongoDatabase db;
	private static MongoClient mongo;
	private static MongoClient mongo_Log;
	private static MongoCollection<Document> col;
	private static MongoCollection<Document> col1;
	private static MongoCollection<Document> col2;

	public static void init() {
		if (inited) {
			return;
		}
		mongo = new MongoClient(new MongoClientURI("mongodb://" + account + ":" + passwd + "@" + dbHost + "/admin"));
		db = mongo.getDatabase(dbName);
		col = db.getCollection(colName);
		col1 = db.getCollection(colName1);
		col2 = db.getCollection(colName2);
		inited = true;
	}

	/**
	 * <匯出高德地圖設施網點信息數據>
	 * <p>各個標準市場的所有設施小類的終端數量
	 * 
	 * @param List<BizUnit> 所有大區對象
	 * @param List<PoiListQueryStringVO> 預設設施小類的查詢字串
	 * 
	 * @author 80005121
	 * 
	 */
	static void exportPoiTypeGroupCount(List<BizUnit> units, List<PoiListQueryStringVO> poiListQueryStrings) {
		System.out.println("=============================== Start to find group count ===============================");

		Map<String, List<List<Document>>> map = new HashMap<>();

		try {
			List<Document> list = new ArrayList<>();
			
			//讀出查詢mongo db的每筆數據
			Block<Document> processBlock = new Block<Document>() {
				@Override
				public void apply(final Document document) {
					list.add(document);
				}
			};

			//封裝預設設施小類的查詢字串
			List<Pattern> patternList = new ArrayList<>();
			for (PoiListQueryStringVO vo : poiListQueryStrings) {
				patternList.add(Pattern.compile(vo.getQueryString()));
			}

			for (BizUnit zone : units) {//查詢每個大區，依照標準市場及設施小類分組的終端數量
				List<Document> mapList = new ArrayList<>();
				long begin = System.currentTimeMillis();
				List<? extends Bson> pipeline = Arrays.asList(
						//stage 1 : $match
						new Document().append("$match",
										new Document().append("districtID", zone.getId())
										.append("poiTypeList.2", new Document().append("$in", patternList))),
						//stage 2 : $unwind
						new Document().append("$unwind", "$poiTypeGroups"),
						//stage 3 : $group
						new Document().append("$group", 
								new Document().append("_id",
										new Document().append("districtID", "$districtID")
												.append("districtName", "$districtName")
												.append("companyID", "$companyID")
												.append("companyName", "$companyName")
												.append("branchID", "$branchID")
												.append("branchName", "$branchName")
												.append("marketID", "$marketID")
												.append("marketName", "$marketName")
												.append("poiTypeGroups", "$poiTypeGroups"))
								.append("count", new Document().append("$sum", 1))),
						//stage 4 : $sort
						new Document().append("$sort",
								new Document().append("marketID", 1.0)
								.append("poiTypeGroups.2", 1.0)));

				//因數據量太多，allowDiskUse需設為true
				col.aggregate(pipeline).allowDiskUse(true).forEach(processBlock);

				// 依標準市場編碼及設施小類排序
				Collections.sort(list, new Comparator<Document>() {
					public int compare(Document o1, Document o2) {
						String market1 = ((Document) (o1.get("_id"))).getString("marketID");
						String market2 = ((Document) (o2.get("_id"))).getString("marketID");
						@SuppressWarnings("unchecked")
						String poiType1 = ((List<String>) ((Document) (o1.get("_id"))).get("poiTypeGroups")).get(2);
						@SuppressWarnings("unchecked")
						String poiType2 = ((List<String>) ((Document) (o2.get("_id"))).get("poiTypeGroups")).get(2);

						int i = market1.compareTo(market2);
						if (i == 0) {
							return poiType1.compareTo(poiType2);
						} else {
							return i;
						}
					}
				});

				System.out.println("Find " + zone.getName() + " " + list.size() + " data spend "
						+ (System.currentTimeMillis() - begin) / 1000 + " seconds");
				
				if (list.size() > 0) {//若該大區有數據
					mapList.addAll(list);
					map.put(zone.getName(), Collections.singletonList(mapList));
					list.clear();
				} else {
					System.out.println(zone.getName() + " 查無數據 ！");
					map.put(zone.getName(), null);
				}
				Thread.sleep(200);
			}
			exportExcel(map);
		} catch (Exception e) {
			e.fillInStackTrace();
		}
	}

	private static HSSFCellStyle createFieldCellStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("IMPACT");
//		font.setBold(true);
		HSSFCellStyle fieldCellStyle = workbook.createCellStyle();
		fieldCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		fieldCellStyle.setVerticalAlignment(CellStyle.ALIGN_CENTER);
		fieldCellStyle.setFillBackgroundColor(HSSFColor.GREY_25_PERCENT.index);
		fieldCellStyle.setWrapText(true);
		fieldCellStyle.setFont(font);
		return fieldCellStyle;
	}

	private static HSSFCellStyle createRowCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle rowCellStyle = workbook.createCellStyle();
		rowCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		rowCellStyle.setVerticalAlignment(CellStyle.ALIGN_GENERAL);
		rowCellStyle.setWrapText(false);
		return rowCellStyle;
	}

	private static void exportExcel(Map<String, List<List<Document>>> map) {
		System.out.println("========================== Start to export excel ==========================");
		String[] headers = { "大区", "分公司", "营业所", "标准市场", "设施大类", "设施中类", "设施小类", "终端数" };

		//取得大區名稱
		Set<String> keySet = map.keySet();
		System.out.println("key set = " + keySet);
		
		//取得當前年月
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		
		String filePath = PropertiesUtil.getExcelProperties().getProperty("path") + "/高德地图设施网点信息数据索取/" + year + "年" + month + "月份/";
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("新增" + filePath + "文件夾");
			file.mkdirs();
		}

		for (String sheetTitle : keySet) {
			try (FileOutputStream output = new FileOutputStream(filePath + "/" + sheetTitle + "设施网点信息数据索取.xls")) {
				// 声明一个工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFCellStyle fieldCellStyle = createFieldCellStyle(wb);
				HSSFCellStyle rowCellStyle = createRowCellStyle(wb);
				HSSFCellStyle cellStyle=wb.createCellStyle(); 
				cellStyle.setWrapText(true);
				// 生成一个表格
				System.out.println("生成 " + sheetTitle + " 頁");
				if (null != map.get(sheetTitle) && map.get(sheetTitle).size() > 0) {
					List<Document> list = map.get(sheetTitle).get(0);
					
					//若數據量超過SHEET_LIMIT，則新增分頁
					int pages = ((list.size() % SHEET_LIMIT) > 0) ? (list.size() / SHEET_LIMIT) + 1 : (list.size() / SHEET_LIMIT);
					
					for (int i = 0; i < pages; i++) {
						String newSheetTitle = sheetTitle;
						
						if (pages > 1) {//需新增分頁
							newSheetTitle += "_" + (i + 1);
						}
						
						HSSFRow row;
						HSSFSheet sh = setHeadTitle(headers, wb, fieldCellStyle, newSheetTitle);
						
						for (int j = i * SHEET_LIMIT + 0; j < (((i + 1) * SHEET_LIMIT > list.size()) ? list.size() : (i + 1) * SHEET_LIMIT); j++) {
							Document doc = (Document) list.get(j).get("_id");
							row = sh.createRow(sh.getPhysicalNumberOfRows());
							HSSFCell cell = row.createCell(0);
							cell.setCellValue(doc.getString("districtName"));
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(1);
							cell.setCellValue(doc.getString("companyName"));
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(2);
							cell.setCellValue(doc.getString("branchName"));
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(3);
							cell.setCellValue(doc.getString("marketName"));
							cell.setCellStyle(rowCellStyle);
							@SuppressWarnings("unchecked")
							List<String> typeList = (List<String>) doc.get("poiTypeGroups");
							cell = row.createCell(4);
							cell.setCellValue(typeList.get(0));
							cell.setCellStyle(cellStyle);
							cell = row.createCell(5);
							cell.setCellValue(typeList.get(1));
							cell.setCellStyle(cellStyle);
							cell = row.createCell(6);
							cell.setCellValue(typeList.get(2));
							cell.setCellStyle(cellStyle);
							cell = row.createCell(7);
							cell.setCellValue(list.get(j).getInteger("count"));
							cell.setCellStyle(rowCellStyle);
						}
					}
				} else {
					HSSFSheet sh = setHeadTitle(headers, wb, fieldCellStyle, sheetTitle);
					System.out.println("查無數據");
					HSSFRow row_3 = sh.createRow(headers.length + 1);
					row_3.setHeight((short) 900);
					HSSFCell cell_3 = row_3.createCell(0);
					cell_3.setCellStyle(rowCellStyle);
					HSSFRichTextString text_3 = new HSSFRichTextString("查无数据!");
					cell_3.setCellValue(text_3);
				}
				wb.write(output);
				output.flush();
				output.close();
//				wb.close();
				System.out.println("成功创建excel文件");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static HSSFSheet setHeadTitle(String[] headers, HSSFWorkbook wb, HSSFCellStyle fieldCellStyle,
			String sheetTitle) {
		HSSFSheet sh = wb.createSheet(sheetTitle);
		HSSFRow row = sh.createRow(sh.getPhysicalNumberOfRows());
		for (int i = 0; i < headers.length; i++) {
			switch (i) {
			case 6:
				sh.setColumnWidth(i, 8000);
				break;
			default:
				sh.setColumnWidth(i, 6000);
			}

			HSSFCell cell = row.createCell(i);
			cell.setCellValue(headers[i]);
			cell.setCellStyle(fieldCellStyle);
		}
		return sh;
	}

	public static MongoCollection<Document> getCol() {
		return col;
	}

	public static void setCol(MongoCollection<Document> col) {
		MongoUtils.col = col;
	}

	public static void close() {
		if (mongo != null) {
			mongo.close();
			mongo = null;
		}
		if (mongo_Log != null) {
			mongo_Log.close();
			mongo_Log = null;
		}
	}

	public static MongoDatabase getDB() {
		return db;
	}

	public static Document getLastIndexLog() {
		Document log = null;
		for (Document doc : col2.find().sort(new BasicDBObject("update", -1)).limit(1)) {
			log = doc;
		}
		return log;
	}

	public static List<PoiUnit> listPois(String compId, String branchId) {
		Document q = new Document("companyID", compId);
		if (branchId != null) {
			q = q.append("branchID", branchId);
		}
		List<PoiUnit> pois = new ArrayList<PoiUnit>();

		// 高德终端
		System.out.println("\n\nStart to add " + colName + "\n\n");
		for (Document cur : col.find(q)) {
			PoiUnit poi = docToPoiUnit(cur, "1");
			pois.add(poi);
		}

		// 集团终端
		System.out.println("\n\nStart to add " + colName1 + "\n\n");
		for (Document cur : col1.find(q)) {
			PoiUnit poi = docToPoiUnit(cur, "2");
			pois.add(poi);
		}

		return pois;
	}

	private static PoiUnit docToPoiUnit(Document cur, String poiCategory) {
		PoiUnit poi = new PoiUnit();
		String companyId = cur.getString("companyID");
		poi.setCompId(companyId);
		String branchId = cur.getString("branchID");
		poi.setBranchId(branchId);
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
		String towncode = cur.getString("towncode");
		poi.setTowncode(towncode);
		String township = cur.getString("township");
		poi.setTownship(township);
		
		StringBuffer newPoiTypeListString = new StringBuffer("");
		if (cur.get("poiTypeGroups") != null) {
			@SuppressWarnings("unchecked")
			List<List<String>> groupList = (List<List<String>>) cur.get("poiTypeGroups");
			for (List<String> list : groupList) {
				newPoiTypeListString.append("【");
				for (String type : list) {
					newPoiTypeListString.append(type).append(",");
				}
				newPoiTypeListString.replace(newPoiTypeListString.length() - 1, newPoiTypeListString.length(), "】,");
			}
			newPoiTypeListString = newPoiTypeListString.delete(newPoiTypeListString.length() - 1, newPoiTypeListString.length());
		}
		String poiTypeListString = newPoiTypeListString.toString();
		
		poi.setPoiTypeListString(poiTypeListString);

		if (cur.get("poiTypeList") != null) {
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
		String telMappingList = cur.getString("telMappingList");
		poi.setTelMappingList(telMappingList);
		poi.setPoiCategory(poiCategory);
		String longitude = (null == cur.getString("longitude")) ? "" : cur.getString("longitude");
		poi.setLongitude(longitude);
		String latitude = (null == cur.getString("latitude")) ? "" : cur.getString("latitude");
		poi.setLatitude(latitude);

		return poi;
	}

}
