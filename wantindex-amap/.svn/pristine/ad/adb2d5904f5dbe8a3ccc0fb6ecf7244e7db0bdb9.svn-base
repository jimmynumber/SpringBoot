package com.want.amap.mongo;

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
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.want.amap.oracle.OracUtil;
import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.PoiListQueryStringVO;
import com.want.amap.vo.PoiUnit;

public class SearchPoiIndex {
	private static Analyzer analyzer;
	private static Directory dir;
	private static DirectoryReader ireader;
	private static IndexSearcher searcher;

	public static void main(String[] args) {
		try {
			init();
//			System.out.println(listByCompany("W19"));
			OracUtil.getConn();
			System.out.println(countByDefaultPoiType(OracUtil.getDefaultPoiType()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Integer> countByDefaultPoiType(List<PoiListQueryStringVO> poiTypeList) throws Exception {
		Map<String, Integer> map = new HashMap<>();
		BooleanQuery.Builder builder = new BooleanQuery.Builder();
		StringBuffer queryString = new StringBuffer();
		queryString.append("poiCategory:\"2\" or ");
		queryString.append("poiType3:");
		for (PoiListQueryStringVO p : poiTypeList) {
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
		map.put("defaultTypeNum", poiTypeList.size());
		
		return map;
	}

	public static List<PoiUnit> listByType1(String compId, String branchId, String marketId, String smallMarketId,
			String type1) throws Exception {
		TermQuery query1 = new TermQuery(new Term("compId", compId));
		TermQuery query2 = new TermQuery(new Term("branchId", branchId));
		TermQuery query3 = new TermQuery(new Term("marketId", marketId));
		TermQuery query4 = new TermQuery(new Term("smallMarketId", smallMarketId));
		TermQuery query5 = new TermQuery(new Term("poiType1", type1));
		BooleanQuery bq = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.MUST)
				.add(query2, BooleanClause.Occur.MUST).add(query3, BooleanClause.Occur.MUST)
				.add(query4, BooleanClause.Occur.MUST).add(query5, BooleanClause.Occur.MUST).build();
		TopDocs topDocs = searcher.search(bq, 500000);
		List<PoiUnit> pois = new ArrayList<PoiUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			PoiUnit poi = parsePoi(doc);
			pois.add(poi);
		}
		System.out.println(topDocs.totalHits);
		return pois;
	}

	public static List<PoiUnit> listBySmallMarket(String compId, String branchId, 
			String marketId, String smallMarketId) throws Exception {
		if (compId == null) {
			throw new Exception("Empty Company ID!");
		}
		Query q = null;
		TermQuery query1 = new TermQuery(new Term("compId", compId));
		if (branchId != null) {
			TermQuery query2 = new TermQuery(new Term("branchId", branchId));
			BooleanQuery.Builder builder = new BooleanQuery.Builder();
			builder.add(query1, BooleanClause.Occur.MUST);
			builder.add(query2, BooleanClause.Occur.MUST);
			if (marketId != null) {
				TermQuery query3 = new TermQuery(new Term("marketId", marketId));
				builder.add(query3, BooleanClause.Occur.MUST);
				if (smallMarketId != null) {
					TermQuery query4 = new TermQuery(new Term("smallMarketId", smallMarketId));
					builder.add(query4, BooleanClause.Occur.MUST);
				}
			}
			BooleanQuery bq = builder.build();
			q = bq;
		} else {
			q = query1;
		}
		TopDocs topDocs = searcher.search(q, 500);
		List<PoiUnit> pois = new ArrayList<PoiUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			PoiUnit poi = parsePoi(doc);
			pois.add(poi);
		}
		System.out.println(topDocs.totalHits);
		return pois;
	}

	public static List<PoiUnit> listByMarket(String compId, String branchId, String marketId) throws Exception {
		TermQuery query1 = new TermQuery(new Term("compId", compId));
		TermQuery query2 = new TermQuery(new Term("branchId", branchId));
		TermQuery query3 = new TermQuery(new Term("marketId", marketId));
		BooleanQuery bq = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.MUST)
				.add(query2, BooleanClause.Occur.MUST).add(query3, BooleanClause.Occur.MUST).build();
		TopDocs topDocs = searcher.search(bq, 500000);
		List<PoiUnit> pois = new ArrayList<PoiUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			PoiUnit poi = parsePoi(doc);
			pois.add(poi);
		}
		System.out.println(topDocs.totalHits);
		return pois;
	}

	public static List<PoiUnit> listByBranch(String compId, String branchId) throws Exception {
		TermQuery query1 = new TermQuery(new Term("compId", compId));
		TermQuery query2 = new TermQuery(new Term("branchId", branchId));
		BooleanQuery bq = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.MUST)
				.add(query2, BooleanClause.Occur.MUST).build();
		TopDocs topDocs = searcher.search(bq, 500000);
		List<PoiUnit> pois = new ArrayList<PoiUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			PoiUnit poi = parsePoi(doc);
			pois.add(poi);
		}
		System.out.println(topDocs.totalHits);
		return pois;
	}

	public static List<PoiUnit> listByCompany(String compId) throws Exception {
		TermQuery query = new TermQuery(new Term("companyId", compId));
		TopDocs topDocs = searcher.search(query, 500000);
		List<PoiUnit> pois = new ArrayList<PoiUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			PoiUnit poi = parsePoi(doc);
			pois.add(poi);
		}
		System.out.println(topDocs.totalHits);
		return pois;
	}

	private static PoiUnit parsePoi(Document doc) {
		PoiUnit poi = new PoiUnit();
		poi.setDistrictName(doc.get("districtName"));
		poi.setCompName(doc.get("compName"));
		poi.setBranchId(doc.get("branchId"));
		poi.setBranchName(doc.get("branchName"));
		poi.setMarketName(doc.get("marketName"));
		poi.setSmallMarketName(doc.get("smallMarketName"));
		poi.setPoiType1(doc.get("poiType1"));
		poi.setPoiType2(doc.get("poiType2"));
		poi.setPoiType3(doc.get("poiType3"));
		poi.setPoiName(doc.get("poiName"));
		poi.setPoiAddress(doc.get("poiAddress"));
		poi.setPoiRealAddress(doc.get("poiRealAddress"));
		poi.setPoiId(doc.get("poiId"));
		poi.setPoiTel(doc.get("poiTel"));
		return poi;
	}

	public static void init() throws Exception {
//		dir = FSDirectory.open(Paths.get("/Users/80005121/Documents/img/lucene_index/poi_index_mapping"));
		analyzer = new AnsjAnalyzer(TYPE.index_ansj);
		dir = FSDirectory.open(Paths.get(PropertiesUtil.getMongoProperties().getProperty("path")));
		ireader = DirectoryReader.open(dir);
		searcher = new IndexSearcher(ireader);
	}

	public static void close() {
		try {
			dir.close();
			ireader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dir = null;
	}
	
	

}
