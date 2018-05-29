package com.zyx.collection;

import java.util.EnumSet;

public class EnumSetTest {

	public static void main(String[] args){
		
		EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
		EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
		EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
		EnumSet<Weekday> mwf = EnumSet.of(Weekday.MONDAY, Weekday.WENESDAY, Weekday.FRIDAY);
		
		// print MONDAY-TUESDAY-WENESDAY-THURSDAY-FRIDAY-SATURDAY-SUNDAY-
		print(always);
		// print 
		print(never);
		// print MONDAY-TUESDAY-WENESDAY-THURSDAY-FRIDAY-
		print(workday);
		// print MONDAY-WENESDAY-FRIDAY-
		print(mwf);
	}

	private static void print(EnumSet<Weekday> enumSet) {
		for(Weekday day : enumSet){
			System.out.print(day + "-");
		}
		System.out.println("");
	}
}
enum Weekday{MONDAY, TUESDAY, WENESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};

