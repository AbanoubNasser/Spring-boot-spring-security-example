package com.mondiamedia.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date addDays(Date date,int days) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(new Date()); 
		calender.add(Calendar.DATE, days);
		return calender.getTime();
	}
}
