package com.want.amap.lucene.impl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.want.amap.domain.AllPoiInfo;
import com.want.amap.domain.GaodeMapPoiAddressAllAdWithThirdForthAllAd;
import com.want.amap.lucene.IndexAdapter;
import com.want.amap.repository.AllPoiInfoRepository;
import com.want.amap.repository.GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository;
import com.want.amap.vo.BizUnit;
import com.want.amap.vo.PoiListVO;
import com.want.amap.vo.PoiUnit;

@Service
public class GaodeIndex implements IndexAdapter<List<BizUnit>> {
	private  Analyzer analyzer;
	private  Directory dir;
	private  IndexWriter iw;
	private DirectoryReader ireader;
	private IndexSearcher searcher;
	
	@Autowired
	private OrganizationIndex organizationIndex;
	
	@Value("${gaodeIndexPath}")
	private String gaodeIndexPath;
	
	@Autowired
	private GaodeMapPoiAddressAllAdWithThirdForthAllAdRepository gaodeMapPoiAddressAllAdWithThirdForthAllAdRepository;
	
	@Autowired
	private AllPoiInfoRepository allPoiInfoRepository;
	
	@Override
	public void init() throws IOException {
		analyzer = new AnsjAnalyzer(TYPE.index_ansj);
		dir = FSDirectory.open(Paths.get(gaodeIndexPath));
		iw = new IndexWriter(dir, new IndexWriterConfig(analyzer));
	}

	@Override
	public int add(List<BizUnit> comps) throws IOException {
		int count = 1;
		int poiCount = 0;

		try {
			for (BizUnit comp : comps) {
				String compId = comp.getId();
				System.out.println("Adding " + compId + " (" + count + "/" + comps.size() + ")");
				count++;
				List<BizUnit> branches = organizationIndex.getChilds(compId);
				
				for (BizUnit branch : branches) {
					System.out.println(" Adding " + branch.getName() + "...");
					// 取得數據來源
					List<PoiUnit> pois = listPois(compId, branch.getId());
					System.out.println(branch.getName() + " 共有 " + pois.size() + " 筆設施信息");

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
			iw.forceMergeDeletes();
			iw.commit();
		} catch (Exception e) {
			e.printStackTrace();
			poiCount = -1;
		}
		return poiCount;
	}

	private List<PoiUnit> listPois(String compId, String branchId) {
		List<PoiUnit> pois = new ArrayList<PoiUnit>();

		// 高德终端
		System.out.println("\n\nStart to add GaodeMapPoiAddressAllAdWithThirdForth_AllAd\n\n");
		for (GaodeMapPoiAddressAllAdWithThirdForthAllAd rep : gaodeMapPoiAddressAllAdWithThirdForthAllAdRepository.findById(compId, branchId)) {
			PoiUnit poi = docToPoiUnit(rep, "1");
			pois.add(poi);
		}

		// 集团终端
		System.out.println("\n\nStart to add AllPoiInfo\n\n");
		for (AllPoiInfo rep : allPoiInfoRepository.findByCompanyIdAndBranchId(compId, branchId)) {
			PoiUnit poi = docToPoiUnit(rep, "2");
			pois.add(poi);
		}

		return pois;
	}
	
	private<T extends GaodeMapPoiAddressAllAdWithThirdForthAllAd> PoiUnit docToPoiUnit(T rep, String poiCategory) {
		PoiUnit poi = null;
		
		if (rep != null) {
			poi = new PoiUnit();
			poi.setBranchId(rep.getBranchID());
			poi.setBranchName(rep.getBranchName());
			poi.setCompId(rep.getCompanyID());
			poi.setCompName(rep.getCompanyName());
			poi.setDistrictName(rep.getDistrictName());
			poi.setLatitude(rep.getLatitude());
			poi.setLongitude(rep.getLongitude());
			poi.setMarketId(rep.getMarketID());
			poi.setMarketName(rep.getMarketName());
			poi.setPoiAddress(rep.getPoiAddress());
			poi.setPoiCategory(poiCategory);
			poi.setPoiId(rep.getPoiID());
			poi.setPoiName(rep.getPoiName());
			poi.setPoiRealAddress(rep.getPoiRealAddress());
			poi.setPoiTel(rep.getPoiTel());
			List<String> typeList = rep.getPoiTypeList();
			if (typeList != null && typeList.size() > 0) {
				poi.setPoiType1(typeList.get(0));
				poi.setPoiType2(typeList.get(1));
				poi.setPoiType3(typeList.get(2));
			}
			StringBuffer newPoiTypeListString = new StringBuffer("");
			if (rep.getPoiTypeGroups() != null && rep.getPoiTypeGroups().size() > 0) {
				List<List<String>> groupList = rep.getPoiTypeGroups();
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
			poi.setSmallMarketId(rep.getSmallMarketID());
			poi.setSmallMarketName(rep.getSmallMarketName());
			poi.setTelMappingList(rep.getTelMappingList());
			poi.setTowncode(rep.getTowncode());
			poi.setTownship(rep.getTownship());
			poi.setWantForthId(rep.getWantForthID());
			poi.setWantForthName(rep.getWantForthName());
			poi.setWantThirdId(rep.getWantThirdID());
			poi.setWantThirdName(rep.getWantThirdName());
		}
		
		return poi;
	}
	
	public Map<String, Integer> countByDefaultPoiType(List<PoiListVO> defaultPoiTypes) throws ParseException, IOException {
		if (ireader == null) {
			ireader = DirectoryReader.open(dir);
		}
		if (searcher == null) {
			searcher = new IndexSearcher(ireader);
		}
		Map<String, Integer> map = new HashMap<>();
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		StringBuffer queryString = new StringBuffer();
		queryString.append("poiCategory:\"2\" or ");
		queryString.append("poiType3:");
		for (PoiListVO p : defaultPoiTypes) {
			for (String s : p.getLV3().split(",")) {
				queryString.append("\"" + QueryParser.escape(s) + "\" or ");
			}
		}
		queryString.delete(queryString.length()-3, queryString.length());
		builder.add(new QueryParser("poiType3", analyzer).parse(queryString.toString()), BooleanClause.Occur.MUST);
		Query q = builder.build();
		TopDocs topDocs = searcher.search(q, 5000);
		
		map.put("validNum", Math.toIntExact(topDocs.totalHits));
		map.put("indexNum", searcher.getIndexReader().maxDoc());
		map.put("defaultTypeNum", defaultPoiTypes.size());
		
		return map;
	}

	@Override
	public void close() {
		try {
			if (iw != null) {
				iw.close();
			}
			if (dir != null) {
				dir.close();
			}
			if (analyzer != null) {
				analyzer.close();
			}
			if (ireader != null) {
				ireader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dir = null;
		iw = null;
		analyzer = null;
		ireader = null;
	}

}
