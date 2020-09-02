package com.want.amap.repository;

import java.util.List;

public class Location {
	private String type;
	private List<String> coordinates;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(List<String> coordinates) {
		this.coordinates = coordinates;
	}
	@Override
	public String toString() {
		return "Location [type=" + type + ", coordinates=" + coordinates + "]";
	}
	
}
