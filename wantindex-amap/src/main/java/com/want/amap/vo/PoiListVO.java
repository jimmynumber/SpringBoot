package com.want.amap.vo;

public class PoiListVO {
	private String LV1;

	private String LV2;

	private String LV3;
	
	private String queryString;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@Override
	public String toString() {
		return "PoiListVO [LV1=" + LV1 + ", LV2=" + LV2 + ", LV3=" + LV3 + ", queryString=" + queryString + "]";
	}

	public String getLV1() {
		return LV1;
	}

	public void setLV1(String lV1) {
		LV1 = lV1;
	}

	public String getLV2() {
		return LV2;
	}

	public void setLV2(String lV2) {
		LV2 = lV2;
	}

	public String getLV3() {
		return LV3;
	}

	public void setLV3(String lV3) {
		LV3 = lV3;
		setQueryString(lV3.replace("(", "\\(").replace(")", "\\)"));
//		setQueryString("/.*" + getLV3() + ".*/");
	}

}
