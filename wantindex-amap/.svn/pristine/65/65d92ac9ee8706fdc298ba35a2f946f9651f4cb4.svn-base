package com.want.amap.util;

import java.io.UnsupportedEncodingException;
import java.net.URL;

public class URLUtil {
	public static String getCodeSourcePath(@SuppressWarnings("rawtypes") Class clazz) {
		URL url = clazz.getProtectionDomain().getCodeSource().getLocation();
		String path = url.getPath();
		try {
			if (path.toUpperCase().endsWith(".JAR")) {
				int index = path.lastIndexOf("/");
				path = path.substring(0, index);
			}
			return java.net.URLDecoder.decode(path, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
}
