package com.want.po;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Paging implements Serializable{

    private int total;
    private int current;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	
}