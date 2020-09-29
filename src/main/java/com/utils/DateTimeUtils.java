/**
 * 
 */
package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author HP
 *
 */
public class DateTimeUtils {
	public static Date getDate(final String stringDate) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		final String dateInString = stringDate;
		final Date date = formatter.parse(dateInString);
		return date;
	}

	public static String getFormattedDate(final Date date) throws ParseException {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));

		final String formattedDateString = formatter.format(date);
		return formattedDateString;
	}

	public static Date getTodayDate() {
		final SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy", Locale.ENGLISH);
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		
		return new Date();

	}
}
