/**
 * -------------------------------------------------------
 * @FileName：TestRunner.java
 * @Description：简要描述本文件的内容
 * @Author：Luke.Tsai
 * @Copyright  www.want-want.com  Ltd.  All rights reserved.
 * 注意：本内容仅限于旺旺集团内部传阅，禁止外泄以及用于其他商业目的
 * -------------------------------------------------------
 */
package com.want.amap;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.want.amap.domain.IndexLog;
import com.want.amap.lucene.impl.GaodeIndex;
import com.want.amap.lucene.impl.OrganizationIndex;
import com.want.amap.mapper.mysql.DataDivisionToMarketMapper;
import com.want.amap.mapper.oracle.PoiListMapper;
import com.want.amap.repository.GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository;
import com.want.amap.repository.IndexLogRepository;
import com.want.amap.util.SendMailUtils;
import com.want.amap.vo.BizUnit;
import com.want.amap.vo.ExcelVO;
import com.want.amap.vo.PoiListVO;

@Component
public class JobCommandLineRunner implements CommandLineRunner {
	final String COMMAND_EXPORTEXCEL = "exportExcel";
	final int SHEET_LIMIT = 65000;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private DataDivisionToMarketMapper dataDivisionToMarketMapper;

	@Autowired
	private PoiListMapper poiListMapper;

	@Autowired
	private GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository gaodeMapPoiAddressAllAdWithThirdForthAllAdRepository;

	@Autowired
	private IndexLogRepository indexLogRepository;

	@Value("${title}")
	private String[] titles;

	@Value("${excelPath}")
	private String excelPath;

	@Autowired
	private OrganizationIndex organizationIndex;

	@Autowired
	private GaodeIndex gaodeIndex;
	
	@Value("${mail.smtp.host}")
	private String host;
	@Value("${mail.smtp.user}")
	private String user;
	@Value("${mail.smtp.password}")
	private String password;
	@Value("${mail.smtp.from}")
	private String from;
	@Value("${mail.smtp.to}")
	private String[] to;
	private String title = "(通知) 高德地圖索引檔資訊";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... args) {
		System.out.println(
				"\n***************************** 開始執行 *****************************");

		List<BizUnit> zones = null;
		List<PoiListVO> defaultPoiTypes = null;
		
		Calendar ca = Calendar.getInstance();
		if (ca.get(Calendar.DAY_OF_MONTH) > 5) {
			ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) + 1);
		}
		ca.set(Calendar.DAY_OF_MONTH, 5);
		
		StringBuffer mailContent = new StringBuffer();

		try {
			zones = dataDivisionToMarketMapper.getAllZones();
			
			if (args != null && args.length > 0 && args[0].equals(COMMAND_EXPORTEXCEL)) {// 只匯出excel
				System.out.println(
						"***************************** 開始執行 匯出Excel 指令 *****************************");
				mailContent.append("***************************** 開始執行 匯出Excel 指令 *****************************");
				mailContent.append("<br>");
				long time = System.currentTimeMillis();
				
				defaultPoiTypes = poiListMapper.getDefaultPoiType();
				exportExcel(gaodeMapPoiAddressAllAdWithThirdForthAllAdRepository.findExportExcel(zones, defaultPoiTypes, mailContent), ca, mailContent);
				long spend = (System.currentTimeMillis() - time) / 1000;
				System.out.println("***************************** 匯出完成，共花費  "
						+ spend
						+ " 秒 *****************************");
				mailContent.append("<br>");
				mailContent.append("***************************** 匯出完成，共花費  " + spend + " 秒 *****************************");
			} else {
				System.out.println(
						"***************************** 開始執行 製作索引檔 指令 *****************************");
				mailContent.append("***************************** 開始執行 製作索引檔 指令 *****************************");
				String start = sdf.format(new Date());
				// 制作分公司索引
				List<BizUnit> units = listAll();
				
				System.out.println("Load Data Done! " + units.size() + " companies!");
				mailContent.append("<br>");
				mailContent.append("Load Data Done! " + units.size() + " companies!");
				organizationIndex.init();
				System.out.println("Lucene inited!");
				mailContent.append("<br>");
				mailContent.append("Lucene inited!");
				organizationIndex.add(units);
				System.out.println("Index Created!");
				mailContent.append("<br>");
				mailContent.append("Index Created!");

				// 制作终端索引
				gaodeIndex.init();
				List<BizUnit> comps = organizationIndex.getCompanyIds();
				int total = gaodeIndex.add(comps);
				String end = sdf.format(new Date());

				if (total > 0) {
					// 查询预设设施类别总笔数
					defaultPoiTypes = poiListMapper.getDefaultPoiType();
					Map<String, Integer> map = gaodeIndex.countByDefaultPoiType(defaultPoiTypes);
					mailContent.append("<br>");
					mailContent.append("開始時間 = " + start);
					mailContent.append("<br>");
					mailContent.append("結束時間 = " + end);
					mailContent.append("<br>");
					mailContent.append("索引檔總數 = " + map.get("indexNum"));
					mailContent.append("<br>");
					mailContent.append("可用索引檔筆數 = " + map.get("validNum"));
					mailContent.append("<br>");
					mailContent.append("預設設施分類數量 = " + map.get("defaultTypeNum"));

					// 时间、笔数写入mongo
					IndexLog logDoc = new IndexLog();
					logDoc.setIndexNum(new BigDecimal(map.get("indexNum")));
					logDoc.setValidNum(new BigDecimal(map.get("validNum")));
					logDoc.setDefaultTypeNum(new BigDecimal(map.get("defaultTypeNum")));
					logDoc.setStart(start);
					logDoc.setEnd(end);
					
					logDoc.setUpdate(new SimpleDateFormat("yyyy-MM-dd").format(ca.getTime()));

					// 取出上一笔Log数据
					IndexLog log = indexLogRepository.findLastIndexLog(new Sort(Sort.Direction.DESC, "end"));
					System.out.println("index = " + log.getIndexNum());
					System.out.println("valid = " + log.getValidNum());
					System.out.println("defaultTypeNum = " + log.getDefaultTypeNum());
					mailContent.append("<br>");
					mailContent.append("上期索引檔總數 = " + log.getIndexNum());
					mailContent.append("<br>");
					mailContent.append("上期可用索引檔筆數 = " +log.getValidNum());
					mailContent.append("<br>");
					mailContent.append("上期預設設施分類數量 = " + log.getDefaultTypeNum());

					// 计算与上期差异
					logDoc.setIndexDiff(new BigDecimal(map.get("indexNum") - log.getIndexNum().intValue()));
					logDoc.setValidDiff(new BigDecimal(map.get("validNum") - log.getValidNum().intValue()));
					logDoc.setDefaultTypeDiff(
							new BigDecimal(map.get("defaultTypeNum") - log.getDefaultTypeNum().intValue()));
					
					indexLogRepository.insert(logDoc);
					mailContent.append("<br>");
					mailContent.append("上期索引檔總數差異 = " + logDoc.getIndexDiff());
					mailContent.append("<br>");
					mailContent.append("上期可用索引檔筆數差異 = " + logDoc.getValidDiff());
					mailContent.append("<br>");
					mailContent.append("上期預設設施分類數量差異 = " + logDoc.getDefaultTypeDiff());
					System.out.println("Insert mongo success");
					mailContent.append("<br>");
					mailContent.append("Insert mongo success");

					System.out.println(
							"***************************** 開始執行 匯出Excel 指令 *****************************");
					mailContent.append("<br>");
					mailContent.append("***************************** 開始執行 匯出Excel 指令 *****************************");
					long time = System.currentTimeMillis();
					exportExcel(gaodeMapPoiAddressAllAdWithThirdForthAllAdRepository.findExportExcel(zones,
							defaultPoiTypes, mailContent), ca, mailContent);
					long spend = (System.currentTimeMillis() - time) / 1000;
					System.out.println("***************************** 匯出完成，共花費  "
							+ spend
							+ " 秒 *****************************");
					mailContent.append("<br>");
					mailContent.append("***************************** 匯出完成，共花費  "
							+ spend
							+ " 秒 *****************************");
				} else {
					System.out.println("***************************** 無索引檔！ *****************************");
					mailContent.append("<br>");
					mailContent.append("***************************** 無索引檔！ *****************************");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			mailContent.append("<br>");
			mailContent.append(e.getMessage());
		} finally {
			SendMailUtils.getInstance().send(host, user, password, from, to, title + (ca.get(Calendar.MONTH) + 1) + "月份", mailContent.toString(), null);
			organizationIndex.close();
			gaodeIndex.close();
		}
	}

	private List<BizUnit> listAll() {
		List<BizUnit> comps = dataDivisionToMarketMapper.getAllCompany();
		System.out.println(comps);
		for (BizUnit comp : comps) {
			System.out.println(comp.getName());
			findBranch(comp);
			for (BizUnit br : comp.getChilds()) {
				System.out.println(">>" + br.getName());
				findMarket(br);
				for (BizUnit market : br.getChilds()) {
					System.out.println(">>>>" + market.getName());
					findSmallMarket(market);
					for (BizUnit smallMarket : market.getChilds()) {
						System.out.println(">>>>>>" + smallMarket.getName());
					}
				}
			}
		}
		return comps;
	}

	private void findSmallMarket(BizUnit market) {
		List<BizUnit> smallMarkets = dataDivisionToMarketMapper.getAllSmallMarket(market);
		market.setChilds(smallMarkets);
	}

	private void findMarket(BizUnit br) {
		List<BizUnit> markets = dataDivisionToMarketMapper.getAllMarket(br);
		br.setChilds(markets);
	}

	private void findBranch(BizUnit comp) {
		List<BizUnit> brs = new ArrayList<BizUnit>();
		List<BizUnit> comp2s = dataDivisionToMarketMapper.getAllBranch(comp);
		for (BizUnit comp2 : comp2s) {
			System.out.println(comp.getName() + ":" + comp2.getName());
			comp2.setParentId(comp.getId());
			brs.add(comp2);
		}
		comp.setChilds(brs);
	}

	private void exportExcel(Map<String, List<List<ExcelVO>>> map, Calendar ca, StringBuffer mailContent) {
		// 取得大區名稱
		Set<String> keySet = map.keySet();
		System.out.println("key set = " + keySet);

		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH) + 1;

		String filePath = excelPath + year + "年" + month + "月份/";
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("新增" + filePath + "文件夾");
			mailContent.append("<br>");
			mailContent.append("新增" + filePath + "文件夾");
			file.mkdirs();
		}

		for (String sheetTitle : keySet) {
			try (FileOutputStream output = new FileOutputStream(filePath + sheetTitle + "设施网点信息数据索取.xls")) {
				// 声明一个工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				HSSFCellStyle fieldCellStyle = createFieldCellStyle(wb);
				HSSFCellStyle rowCellStyle = createRowCellStyle(wb);
				// 生成一个表格
				System.out.println("生成 " + sheetTitle + " 頁");
				mailContent.append("<br>");
				mailContent.append("生成 " + sheetTitle + " 頁");
				if (null != map.get(sheetTitle) && map.get(sheetTitle).size() > 0) {
					List<ExcelVO> list = map.get(sheetTitle).get(0);

					// 若數據量超過SHEET_LIMIT，則新增分頁
					int pages = ((list.size() % SHEET_LIMIT) > 0) ? (list.size() / SHEET_LIMIT) + 1
							: (list.size() / SHEET_LIMIT);

					for (int i = 0; i < pages; i++) {
						String newSheetTitle = sheetTitle;

						if (pages > 1) {// 需新增分頁
							newSheetTitle += "_" + (i + 1);
						}

						HSSFRow row;
						HSSFSheet sh = setHeadTitle(titles, wb, fieldCellStyle, newSheetTitle);

						for (int j = i * SHEET_LIMIT + 0; j < (((i + 1) * SHEET_LIMIT > list.size()) ? list.size()
								: (i + 1) * SHEET_LIMIT); j++) {
							row = sh.createRow(sh.getPhysicalNumberOfRows());
							HSSFCell cell = row.createCell(0);
							cell.setCellValue(list.get(j).getDistrictName());
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(1);
							cell.setCellValue(list.get(j).getCompanyName());
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(2);
							cell.setCellValue(list.get(j).getBranchName());
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(3);
							cell.setCellValue(list.get(j).getMarketName());
							cell.setCellStyle(rowCellStyle);
//							List<String> typeList = list.get(j).getPoiTypeList();
							List<String> typeList = list.get(j).getPoiTypeGroups();
							cell = row.createCell(4);
//							cell.setCellValue(new HSSFRichTextString(typeList.get(0).replace(",", "\r\n")));// 把逗號換成換行符號
							cell.setCellValue(new HSSFRichTextString(typeList.get(0)));
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(5);
//							cell.setCellValue(new HSSFRichTextString(typeList.get(1).replace(",", "\r\n")));
							cell.setCellValue(new HSSFRichTextString(typeList.get(1)));
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(6);
//							cell.setCellValue(new HSSFRichTextString(typeList.get(2).replace(",", "\r\n")));
							cell.setCellValue(new HSSFRichTextString(typeList.get(2)));
							cell.setCellStyle(rowCellStyle);
							cell = row.createCell(7);
							cell.setCellValue(list.get(j).getCount());
							cell.setCellStyle(rowCellStyle);
						}
					}
				} else {
					HSSFSheet sh = setHeadTitle(titles, wb, fieldCellStyle, sheetTitle);
					System.out.println("查無數據");
					HSSFRow row_3 = sh.createRow(titles.length + 1);
					row_3.setHeight((short) 900);
					HSSFCell cell_3 = row_3.createCell(0);
					cell_3.setCellStyle(rowCellStyle);
					HSSFRichTextString text_3 = new HSSFRichTextString("查无数据!");
					cell_3.setCellValue(text_3);
				}
				wb.write(output);
				output.flush();
				System.out.println("成功创建" + filePath + sheetTitle + "设施网点信息数据索取.xls");
				mailContent.append("<br>");
				mailContent.append("成功创建" + filePath + sheetTitle + "设施网点信息数据索取.xls");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	private static HSSFCellStyle createFieldCellStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontName("IMPACT");
		font.setFontHeightInPoints((short) 14);
		HSSFCellStyle fieldCellStyle = workbook.createCellStyle();
		fieldCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		fieldCellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		fieldCellStyle.setFillBackgroundColor(HSSFColor.GREY_40_PERCENT.index);
		fieldCellStyle.setWrapText(true);
		fieldCellStyle.setFont(font);
		return fieldCellStyle;
	}

	private static HSSFCellStyle createRowCellStyle(HSSFWorkbook workbook) {
		HSSFCellStyle rowCellStyle = workbook.createCellStyle();
		rowCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		rowCellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
		rowCellStyle.setWrapText(true);
		return rowCellStyle;
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

}
