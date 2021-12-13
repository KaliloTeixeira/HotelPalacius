package com.java.hotel_palacius.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date convertToDate(String date) {
		Date newDate = null;
		try {
			newDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}

		return newDate;
	}

	public static Date convertToDateFromSql(String date) {
		Date newDate = null;
		try {
			newDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.getMessage();
		}

		return newDate;
	}

	public static String convertToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}
}
