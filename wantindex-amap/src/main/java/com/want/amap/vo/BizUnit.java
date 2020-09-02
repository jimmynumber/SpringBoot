package com.want.amap.vo;

import java.util.List;

public class BizUnit {
//	public static final int ZONE = 2; // 大區 Z
//	public static final int COMPANY = 3; // 分公司 C(ID)
//	public static final int BRANCH = 5; // 營業所 B(ID)
//	public static final int MARKET = 6;// 標準市場 M(ID)
//	public static final int SMALL_MARKET = 7;// 小標市場 S(ID)

//	private int level;
	private String id;
	private String name;

	private String parentId;
	private List<BizUnit> childs;

	public BizUnit() {
	}

	@Override
	public String toString() {
		return "BizUnit [id=" + id + ", name=" + name + ", parentId=" + parentId + ", childs=" + childs + "]";
	}

	public BizUnit(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

//	public int getLevel() {
//		return level;
//	}
//
//	public void setLevel(int level) {
//		this.level = level;
//	}

	public List<BizUnit> getChilds() {
		return childs;
	}

	public void setChilds(List<BizUnit> childs) {
		this.childs = childs;
	}

}
