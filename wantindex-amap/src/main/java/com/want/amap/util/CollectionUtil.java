package com.want.amap.util;

import java.util.ArrayList;
import java.util.List;

import com.want.amap.vo.ExcelVO;

public class CollectionUtil {

	public static <E> ArrayList<E> convertArrayList(List<E> list) {
		ArrayList<E> returnList = new ArrayList<>();

		for (E e : list) {
			ExcelVO vo = (ExcelVO) e;
			if (vo.getPoiTypeGroups() != null && vo.getPoiTypeGroups().size() == 3) {
				returnList.add(e);
			}
		}
		return returnList;
	}
}
