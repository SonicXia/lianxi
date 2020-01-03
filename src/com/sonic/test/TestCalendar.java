package com.sonic.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Sonic
 */
public class TestCalendar {

	public static void main(String[] args) {

		GregorianCalendar calendar = new GregorianCalendar();
		Date time = calendar.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String format = sdf.format(time);
		System.out.println(format);


	}

}
