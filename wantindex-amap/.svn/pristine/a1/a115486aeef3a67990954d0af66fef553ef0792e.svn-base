package com.want.amap.sql;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.BizUnit;

public class SearchUnitIndex {
	private static Directory dir;
	private static DirectoryReader ireader;
	private static IndexSearcher searcher;
	private static int limit = 1000;

	public static void init() throws Exception {
		dir = FSDirectory.open(Paths.get(PropertiesUtil.getMysqlProperties().getProperty("path")));
//		dir = FSDirectory.open(Paths.get("/Users/80005121/Documents/img/lucene_index/comp_basic"));
		ireader = DirectoryReader.open(dir);
		searcher = new IndexSearcher(ireader);
	}

	public static void close() {
		try {
			if (dir != null) {
				dir.close();
			}
			if (ireader != null) {
				ireader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		dir = null;
	}

	public static List<BizUnit> getChilds(String pid) throws Exception {
		TermQuery query1 = new TermQuery(new Term("type", "unit"));
		TermQuery query2 = new TermQuery(new Term("pid", pid));
		BooleanQuery bq = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.MUST)
				.add(query2, BooleanClause.Occur.MUST).build();
		TopDocs topDocs = searcher.search(bq, limit);
		List<BizUnit> childs = new ArrayList<BizUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			BizUnit bu = new BizUnit();
			bu.setId(doc.get("id"));
			bu.setName(doc.get("name"));
			bu.setParentId(pid);
//			bu.setLevel(Integer.parseInt(doc.get("level")));
			childs.add(bu);
		}
		return childs;
	}

	public static List<BizUnit> getCompanyIds() throws Exception {
		String str = "";
		String str2 = "";
		TermQuery query = new TermQuery(new Term("type", "TopId"));
		TopDocs topDocs = searcher.search(query, 500);
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			str = doc.get("allIds");
			str2 = doc.get("allNames");
		}
		System.out.println(str);
		System.out.println(str2);
		StringTokenizer st = new StringTokenizer(str, ",");
		StringTokenizer st2 = new StringTokenizer(str2, ",");
		if (st.countTokens() == st2.countTokens()) {
			List<BizUnit> comps = new ArrayList<BizUnit>();
			int size = st.countTokens();
			for (int i = 0; i < size; i++) {
				String id = st.nextToken().trim();
				String name = st2.nextToken().trim();
				BizUnit comp = new BizUnit(id, name);
				comps.add(comp);
			}
			return comps;
		} else {
			throw new Exception("Wrong Index [company id not match to company name!]");
		}
	}
}
