package com.want.amap.util;

import java.io.File;
import java.io.FileReader;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.want.amap.mongo.MongoToIndex;

public class PropertiesUtil {
	private static Map<String, String> props = new ConcurrentHashMap<String, String>();

	static {
		getMongoProperties();
		getMysqlProperties();
		getOracleProperties();
		getExcelProperties();
	}
	
	public static Properties getExcelProperties() {
		Properties config = new Properties();
		try {
			String classPath = URLUtil.getCodeSourcePath(MongoToIndex.class);
			
			config.load(new FileReader(new File(new File(classPath), "conf/excel.properties")));
			
			if (config.size() > 0) {
				for (Object prop : config.keySet()) {
					props.put(String.valueOf(prop), config.getProperty(String.valueOf(prop)));
				}
			}
		} catch (Exception e) {
			System.out.println(String.format("******初始化配置文件失败,请检查文件[%s]在当前目录下存在******", "conf/excel.properties"));
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return config;
	}
	
	public static Properties getOracleProperties() {
		Properties config = new Properties();
		try {
			String classPath = URLUtil.getCodeSourcePath(MongoToIndex.class);
			
			config.load(new FileReader(new File(new File(classPath), "conf/oracle.properties")));
			
			if (config.size() > 0) {
				for (Object prop : config.keySet()) {
					props.put(String.valueOf(prop), config.getProperty(String.valueOf(prop)));
				}
			}
		} catch (Exception e) {
			System.out.println(String.format("******初始化配置文件失败,请检查文件[%s]在当前目录下存在******", "conf/mongo.properties"));
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return config;
	}

	public static Properties getMongoProperties() {
		Properties config = new Properties();
		try {
			String classPath = URLUtil.getCodeSourcePath(MongoToIndex.class);
			
			config.load(new FileReader(new File(new File(classPath), "conf/mongo.properties")));
			
			if (config.size() > 0) {
				for (Object prop : config.keySet()) {
					props.put(String.valueOf(prop), config.getProperty(String.valueOf(prop)));
				}
			}
		} catch (Exception e) {
			System.out.println(String.format("******初始化配置文件失败,请检查文件[%s]在当前目录下存在******", "conf/mongo.properties"));
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return config;
	}

	public static Properties getMysqlProperties() {
		Properties config = new Properties();
		try {
			String classPath = URLUtil.getCodeSourcePath(MongoToIndex.class);
			
			config.load(new FileReader(new File(new File(classPath), "conf/mysql.properties")));
			
			if (config.size() > 0) {
				for (Object prop : config.keySet()) {
					props.put(String.valueOf(prop), config.getProperty(String.valueOf(prop)));
				}
			}
		} catch (Exception e) {
			System.out.println(String.format("******初始化配置文件失败,请检查文件[%s]在当前目录下存在******", "conf/mysql.properties"));
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return config;
	}

	/**
	 * 取得properties內指定 key 對應的 value
	 * 
	 * @param properties'
	 *            key
	 * @return properties' value
	 */
	public static String getProperty(String key) {
		return props.get(key);
	}
}
