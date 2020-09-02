package com.want.amap.sql;

import java.nio.file.Paths;
import java.util.List;

import org.ansj.lucene7.AnsjAnalyzer;
import org.ansj.lucene7.AnsjAnalyzer.TYPE;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.want.amap.util.PropertiesUtil;
import com.want.amap.vo.BizUnit;

public class SqlToIndex {
	private static Analyzer analyzer;
	private static Directory dir;
	private static IndexWriter iw;

//	public static void main(String[] args) {
//		try {
//			SqlUtil.init();
//			System.out.println("SQL inited!");
//			List<BizUnit> units = SqlUtil.listAll();
//			System.out.println("Load Data Done! " + units.size() + " companies!");
//			SqlToIndex.init();
//			System.out.println("Lucene inited!");
//			SqlToIndex.addDocs(units);
//			System.out.println("Index Created!");
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		} finally {
//			SqlUtil.close();
//		}
//	}

	public static void init() throws Exception {
		analyzer = new AnsjAnalyzer(TYPE.index_ansj);
		dir = FSDirectory.open(Paths.get(PropertiesUtil.getMysqlProperties().getProperty("path")));
//		dir = FSDirectory.open(Paths.get("/Users/80005121/Documents/img/lucene_index/comp_basic"));
		IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
		iw = new IndexWriter(dir, iwc);
	}

	private static String generateCompanyIds(List<BizUnit> comps) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < comps.size(); i++) {
			BizUnit b = comps.get(i);
			if (i > 0) {
				buffer.append(",");
			}
			buffer.append(b.getId());
		}
		return buffer.toString();
	}

	private static String generateCompanyNames(List<BizUnit> comps) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < comps.size(); i++) {
			BizUnit b = comps.get(i);
			if (i > 0) {
				buffer.append(",");
			}
			buffer.append(b.getName());
		}
		return buffer.toString();
	}

	public static void addDocs(List<BizUnit> comps) throws Exception {
		Document tc = new Document();
		tc.add(new StringField("type", "TopId", Field.Store.NO));
		tc.add(new StringField("allIds", generateCompanyIds(comps), Field.Store.YES));
		tc.add(new StringField("allNames", generateCompanyNames(comps), Field.Store.YES));
		iw.addDocument(tc);
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
			if (comp.getChilds() != null) {
				saveList(comp.getChilds());
			}
		}
		iw.forceMergeDeletes();
		iw.commit();
		iw.close();
		dir.close();
	}

	private static void saveList(List<BizUnit> units) throws Exception {
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
}
