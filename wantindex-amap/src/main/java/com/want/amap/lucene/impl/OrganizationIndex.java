package com.want.amap.lucene.impl;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.ansj.lucene7.AnsjAnalyzer;
import org.ansj.lucene7.AnsjAnalyzer.TYPE;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.want.amap.lucene.IndexAdapter;
import com.want.amap.vo.BizUnit;

@Service
public class OrganizationIndex implements IndexAdapter<List<BizUnit>> {
	static final int LIMIT = 1000;
	
	@Value("${orgIndexPath}")
	private String orgIndexPath;
	
	private AnsjAnalyzer analyzer;
	private Directory dir;
	private IndexWriter iw;
	private DirectoryReader ireader;
	private IndexSearcher searcher;
	
	@Override
	public void init() throws IOException {
		analyzer = new AnsjAnalyzer(TYPE.index_ansj);
		dir = FSDirectory.open(Paths.get(orgIndexPath));
		iw = new IndexWriter(dir, new IndexWriterConfig(analyzer));
	}

	@Override
	public int add(List<BizUnit> comps) throws IOException {
		int ret = 0;
		Document tc = new Document();
		tc.add(new StringField("type", "TopId", Field.Store.NO));
		tc.add(new StringField("allIds", generateCompanyIds(comps), Field.Store.YES));
		tc.add(new StringField("allNames", generateCompanyNames(comps), Field.Store.YES));
		iw.addDocument(tc);
		ret++;
		for (BizUnit comp : comps) {
			Document doc = new Document();
			// 对String类型的字段进行存储，TextField和StringField的不同是TextField既索引又分词
			doc.add(new StringField("type", "unit", Field.Store.NO));
//			doc.add(new StringField("level", comp.getLevel() + "", Field.Store.YES));
			doc.add(new StringField("id", comp.getId() + "", Field.Store.YES));
			doc.add(new StringField("name", comp.getName() + "", Field.Store.YES));
			doc.add(new StringField("pid", comp.getParentId() + "", Field.Store.YES));
			System.out.println("saving..." + comp.getId());
			iw.addDocument(doc);
			ret++;
			if (comp.getChilds() != null) {
				saveList(comp.getChilds());
			}
		}
		iw.forceMergeDeletes();
		iw.commit();
		
		return ret;
	}
	
	public List<BizUnit> getCompanyIds() throws Exception {
		if (ireader == null) {
			ireader = DirectoryReader.open(dir);
		}
		if (searcher == null) {
			searcher = new IndexSearcher(ireader);
		}
		String str = "";
		String str2 = "";
		TermQuery query = new TermQuery(new Term("type", "TopId"));
		TopDocs topDocs = searcher.search(query, 500);
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			str = doc.get("allIds");
			str2 = doc.get("allNames");
		}
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

	private void saveList(List<BizUnit> units) throws IOException {
		for (BizUnit comp : units) {
			Document doc = new Document();
			doc.add(new StringField("type", "unit", Field.Store.NO));
//			doc.add(new StringField("level", comp.getLevel() + "", Field.Store.YES));
			doc.add(new StringField("id", comp.getId() + "", Field.Store.YES));
			doc.add(new StringField("name", comp.getName() + "", Field.Store.YES));
			doc.add(new StringField("pid", comp.getParentId() + "", Field.Store.YES));

			iw.addDocument(doc);
			if (comp.getChilds() != null) {
				saveList(comp.getChilds());
			}
		}
	}

	private String generateCompanyNames(List<BizUnit> comps) {
		StringBuffer sb = new StringBuffer();
		for (BizUnit unit : comps) {
			sb.append(unit.getName()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}

	private String generateCompanyIds(List<BizUnit> comps) {
		StringBuffer sb = new StringBuffer();
		for (BizUnit unit : comps) {
			sb.append(unit.getId()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
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

	public List<BizUnit> getChilds(String pid) throws IOException {
		if (ireader == null) {
			ireader = DirectoryReader.open(dir);
		}
		if (searcher == null) {
			searcher = new IndexSearcher(ireader);
		}
		TermQuery query1 = new TermQuery(new Term("type", "unit"));
		TermQuery query2 = new TermQuery(new Term("pid", pid));
		BooleanQuery bq = new BooleanQuery.Builder().add(query1, BooleanClause.Occur.MUST)
				.add(query2, BooleanClause.Occur.MUST).build();
		TopDocs topDocs = searcher.search(bq, LIMIT);
		List<BizUnit> childs = new ArrayList<BizUnit>();
		for (ScoreDoc score : topDocs.scoreDocs) {
			Document doc = searcher.doc(score.doc);
			BizUnit bu = new BizUnit();
			bu.setId(doc.get("id"));
			bu.setName(doc.get("name"));
			bu.setParentId(pid);
			childs.add(bu);
		}
		return childs;
	}

}
