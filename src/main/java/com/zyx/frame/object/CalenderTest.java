package com.zyx.frame.object;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Date类的实例有一个状态，即特定的【时间点】，用于描述一个时间点。
 * 时间是用距离一个固定时间点的毫秒数（可正可负）表示，这个点就是所谓的纪元（epoch），它是UTC时间1970年1月1日00：00：00。
 * UTC是Coordinated Universal Time的缩写，与GMT(Green Mean Time格林尼治时间)一样，是一种具有实践意义的科学标准时间。
 * Date类所提供的【日期】处理并没有太大的用途。Java类库的设计者认为：像“December 31,2018,23:59:59”这样的日期表示法只是【阳历】的
 * 固有习惯。这种特定的描述发遵循了世界上大多数地区使用的Gregorian阳历表示法。但是，同一【时间点】采用中国的农历表示和采用希伯来的阴历表示
 * 就很不一样，对于火星历来说就更不可想象了。
 * ***将【时间】和【日历】分开是一种很好的面向对象设计。通常，最好使用不同的类表示不同的概念。
 * Date类提供了【时间点】的比较，getDay getMonth getYear历史遗留问题已被废弃。
 * GregorianCalender类包含比Date更多的方法。
 * GregorianCalendar类封装了实例域，这些实例域保存着设置的【日期】信息。
 * 
 * 
 */
public class CalenderTest {

	public static void main(String[] args) {

		Date date = new Date();
		// 之前之后比较
		date.before(new Date());
		date.after(new Date());
		/*
		 * 表示日期的方法已被废弃
		 */
		date.getDate();
		date.getMinutes();
		
		Calendar calendar = new GregorianCalendar();
		// 设置时间点
		calendar.setTime(date);
		/*
		 * 获取日期
		 */
		calendar.get(Calendar.YEAR);
		calendar.get(Calendar.DAY_OF_MONTH);
		
		// 获取时间
		date = calendar.getTime();
	}

}
