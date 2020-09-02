package com.want.amap.lucene;

public interface IndexAdapter<T> {
	
	public void init() throws Exception;
	
	public int add(T t) throws Exception;
	
	public void close();

}
