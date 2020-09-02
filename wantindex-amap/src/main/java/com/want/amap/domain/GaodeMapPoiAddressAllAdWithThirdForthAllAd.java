package com.want.amap.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.want.amap.repository.Location;

@Document(collection = "GaodeMapPoiAddressAllAdWithThirdForth_AllAd")
public class GaodeMapPoiAddressAllAdWithThirdForthAllAd {
	private String poiID;
	private Location location;
	private String poiName;
	private String regionID;
	private String regionName;
	private String areaSecondID;
	private String areaSecondName;
	private String areaThirdID;
	private String areaThirdName;
	private String poiAddress;
	private String poiRealAddress;
	private String poiTel;
	private List<String> poiTypeList;
	private String poiTypeListString;
	private List<List<String>> poiTypeGroups;
	private String crawlDate;
	private String poiItem;
	private String towncode;
	private String township;
	private String districtID;
	private String districtName;
	private String companyID;
	private String companyName;
	private String areaID;
	private String areaName;
	private String branchID;
	private String branchName;
	private String marketID;
	private String marketName;
	private String smallMarketID;
	private String smallMarketName;
	private String wantThirdID;
	private String wantThirdName;
	private String wantForthID;
	private String wantForthName;
	private String longitude;
	private String latitude;
	private String telMappingList;
	private String poiCategory;
	public String getPoiID() {
		return poiID;
	}
	public void setPoiID(String poiID) {
		this.poiID = poiID;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getPoiName() {
		return poiName;
	}
	public void setPoiName(String poiName) {
		this.poiName = poiName;
	}
	public String getRegionID() {
		return regionID;
	}
	public void setRegionID(String regionID) {
		this.regionID = regionID;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getAreaSecondID() {
		return areaSecondID;
	}
	public void setAreaSecondID(String areaSecondID) {
		this.areaSecondID = areaSecondID;
	}
	public String getAreaSecondName() {
		return areaSecondName;
	}
	public void setAreaSecondName(String areaSecondName) {
		this.areaSecondName = areaSecondName;
	}
	public String getAreaThirdID() {
		return areaThirdID;
	}
	public void setAreaThirdID(String areaThirdID) {
		this.areaThirdID = areaThirdID;
	}
	public String getAreaThirdName() {
		return areaThirdName;
	}
	public void setAreaThirdName(String areaThirdName) {
		this.areaThirdName = areaThirdName;
	}
	public String getPoiAddress() {
		return poiAddress;
	}
	public void setPoiAddress(String poiAddress) {
		this.poiAddress = poiAddress;
	}
	public String getPoiRealAddress() {
		return poiRealAddress;
	}
	public void setPoiRealAddress(String poiRealAddress) {
		this.poiRealAddress = poiRealAddress;
	}
	public String getPoiTel() {
		return poiTel;
	}
	public void setPoiTel(String poiTel) {
		this.poiTel = poiTel;
	}
	public List<String> getPoiTypeList() {
		return poiTypeList;
	}
	public void setPoiTypeList(List<String> poiTypeList) {
		this.poiTypeList = poiTypeList;
	}
	public String getPoiTypeListString() {
		return poiTypeListString;
	}
	public void setPoiTypeListString(String poiTypeListString) {
		this.poiTypeListString = poiTypeListString;
	}
	public List<List<String>> getPoiTypeGroups() {
		return poiTypeGroups;
	}
	public void setPoiTypeGroups(List<List<String>> poiTypeGroups) {
		this.poiTypeGroups = poiTypeGroups;
	}
	public String getCrawlDate() {
		return crawlDate;
	}
	public void setCrawlDate(String crawlDate) {
		this.crawlDate = crawlDate;
	}
	public String getPoiItem() {
		return poiItem;
	}
	public void setPoiItem(String poiItem) {
		this.poiItem = poiItem;
	}
	public String getTowncode() {
		return towncode;
	}
	public void setTowncode(String towncode) {
		this.towncode = towncode;
	}
	public String getTownship() {
		return township;
	}
	public void setTownship(String township) {
		this.township = township;
	}
	public String getDistrictID() {
		return districtID;
	}
	public void setDistrictID(String districtID) {
		this.districtID = districtID;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAreaID() {
		return areaID;
	}
	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getMarketID() {
		return marketID;
	}
	public void setMarketID(String marketID) {
		this.marketID = marketID;
	}
	public String getMarketName() {
		return marketName;
	}
	public void setMarketName(String marketName) {
		this.marketName = marketName;
	}
	public String getSmallMarketID() {
		return smallMarketID;
	}
	public void setSmallMarketID(String smallMarketID) {
		this.smallMarketID = smallMarketID;
	}
	public String getSmallMarketName() {
		return smallMarketName;
	}
	public void setSmallMarketName(String smallMarketName) {
		this.smallMarketName = smallMarketName;
	}
	public String getWantThirdID() {
		return wantThirdID;
	}
	public void setWantThirdID(String wantThirdID) {
		this.wantThirdID = wantThirdID;
	}
	public String getWantThirdName() {
		return wantThirdName;
	}
	public void setWantThirdName(String wantThirdName) {
		this.wantThirdName = wantThirdName;
	}
	public String getWantForthID() {
		return wantForthID;
	}
	public void setWantForthID(String wantForthID) {
		this.wantForthID = wantForthID;
	}
	public String getWantForthName() {
		return wantForthName;
	}
	public void setWantForthName(String wantForthName) {
		this.wantForthName = wantForthName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getTelMappingList() {
		return telMappingList;
	}
	public void setTelMappingList(String telMappingList) {
		this.telMappingList = telMappingList;
	}
	public String getPoiCategory() {
		return poiCategory;
	}
	public void setPoiCategory(String poiCategory) {
		this.poiCategory = poiCategory;
	}
	@Override
	public String toString() {
		return "GaodeMapPoiAddressAllAdWithThirdForthAllAd [poiID=" + poiID + ", location=" + location + ", poiName="
				+ poiName + ", regionID=" + regionID + ", regionName=" + regionName + ", areaSecondID=" + areaSecondID
				+ ", areaSecondName=" + areaSecondName + ", areaThirdID=" + areaThirdID + ", areaThirdName="
				+ areaThirdName + ", poiAddress=" + poiAddress + ", poiRealAddress=" + poiRealAddress + ", poiTel="
				+ poiTel + ", poiTypeList=" + poiTypeList + ", poiTypeListString=" + poiTypeListString
				+ ", poiTypeGroups=" + poiTypeGroups + ", crawlDate=" + crawlDate + ", poiItem=" + poiItem
				+ ", towncode=" + towncode + ", township=" + township + ", districtID=" + districtID + ", districtName="
				+ districtName + ", companyID=" + companyID + ", companyName=" + companyName + ", areaID=" + areaID
				+ ", areaName=" + areaName + ", branchID=" + branchID + ", branchName=" + branchName + ", marketID="
				+ marketID + ", marketName=" + marketName + ", smallMarketID=" + smallMarketID + ", smallMarketName="
				+ smallMarketName + ", wantThirdID=" + wantThirdID + ", wantThirdName=" + wantThirdName
				+ ", wantForthID=" + wantForthID + ", wantForthName=" + wantForthName + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", telMappingList=" + telMappingList + ", poiCategory=" + poiCategory
				+ "]";
	}
	
}
