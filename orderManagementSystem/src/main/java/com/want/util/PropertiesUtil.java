/*
 * -------------------------------------------------------
 * @FileName PropertiesUtil.java
 * @Description 配置文件工具类
 * @Author 00294476
 * @Copyright www.want-want.com Ltd. All rights reserved.
 * 注意：本内容仅限于旺旺集团内部传阅，禁止外泄以及用于其他商业目的
 * -------------------------------------------------------
 */
package com.want.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description 签到service层接口,用于初始化webservice
 * @author 00114260
 * @version V1.0.0
 */
public class PropertiesUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtil.class);

	private static Properties properties = new Properties();

	static {
		try {
			properties.load(PropertiesUtil.class.getResourceAsStream("/dev/properties/webservice.properties"));
		} catch (IOException e) {
			LOGGER.error(ExceptionUtil.getTrace(e));
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(key);
	}

}
