package com.zyx.collection;

import java.util.ArrayList;
import java.util.List;

public class SubRangeView {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		
		List<String> subList = list.subList(0, 2);
		// print 1-2-
		for(String number : subList){
			System.out.print(number + "-");
		}
		System.out.println();
		
		// 视图的修改会反映到列表上
		subList.remove(0);
		
		// print 2-3-
		for(String number : list){
			System.out.print(number + "-");
		}
	}

}
