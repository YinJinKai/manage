package com.utill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

	private static final String F_YMDHMS = "yyyy-MM-dd HH:mm:ss";
	private static final String Time = "yyyyMMddHHmmss";
	private static final String shortTime = "HH:mm:ss";
	private static final String shortdate = "yyyy-MM-dd";
	public static String todaydate() {
		DateFormat df = new SimpleDateFormat(shortdate);// 年月日
		Date da = new Date();
		String now = df.format(da);
		return now;

	}
	public static String dateFormate() {
		DateFormat df = new SimpleDateFormat(F_YMDHMS);// 年月日 时分秒
		Date da = new Date();
		String now = df.format(da);
		return now;

	}
	public static String shortdate() {
		DateFormat df = new SimpleDateFormat(shortTime);// 时分秒
		Date da = new Date();
		String now = df.format(da);
		return now;

	}
	public static String datecode() {    // 时间码
		DateFormat df = new SimpleDateFormat(Time);
		Date da = new Date();
		String now = df.format(da);
		return now;
	}
}
