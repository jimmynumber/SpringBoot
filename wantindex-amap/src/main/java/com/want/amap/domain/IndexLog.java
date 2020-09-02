package com.want.amap.domain;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "IndexLog")
public class IndexLog {
	private BigDecimal indexNum;
	private BigDecimal validNum;
	private BigDecimal defaultTypeNum;
	private String start;
	private String end;
	private String update;
	private BigDecimal indexDiff;
	private BigDecimal validDiff;
	private BigDecimal defaultTypeDiff;

	@Override
	public String toString() {
		return "IndexLog [indexNum=" + indexNum + ", validNum=" + validNum + ", defaultTypeNum=" + defaultTypeNum
				+ ", start=" + start + ", end=" + end + ", update=" + update + ", indexDiff=" + indexDiff
				+ ", validDiff=" + validDiff + ", defaultTypeDiff=" + defaultTypeDiff + "]";
	}

	public BigDecimal getIndexNum() {
		return indexNum;
	}

	public void setIndexNum(BigDecimal indexNum) {
		this.indexNum = indexNum;
	}

	public BigDecimal getValidNum() {
		return validNum;
	}

	public void setValidNum(BigDecimal validNum) {
		this.validNum = validNum;
	}

	public BigDecimal getDefaultTypeNum() {
		return defaultTypeNum;
	}

	public void setDefaultTypeNum(BigDecimal defaultTypeNum) {
		this.defaultTypeNum = defaultTypeNum;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public BigDecimal getIndexDiff() {
		return indexDiff;
	}

	public void setIndexDiff(BigDecimal indexDiff) {
		this.indexDiff = indexDiff;
	}

	public BigDecimal getValidDiff() {
		return validDiff;
	}

	public void setValidDiff(BigDecimal validDiff) {
		this.validDiff = validDiff;
	}

	public BigDecimal getDefaultTypeDiff() {
		return defaultTypeDiff;
	}

	public void setDefaultTypeDiff(BigDecimal defaultTypeDiff) {
		this.defaultTypeDiff = defaultTypeDiff;
	}
}
