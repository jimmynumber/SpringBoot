package com.want.wantworld.vo;

public class Menu{
	
	/**
	 * 菜单id
	 */
	private String menuId;
	/**
	 * 父菜单id
	 */
	private String parentId;
	/**
	 * 菜单名称
	 */
	private String menuName;
	/**
	 * 菜单类型
	 */
	private String menuType;
	
	/**
	 * 连接地址
	 */
	private String menuUrl;
	
	/**
	 * 排序
	 */
	private String menuSort;
	/**
	 * 图标
	 */
	private String menuIcon;
	
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public String getMenuSort() {
		return menuSort;
	}
	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}	
	
}
