package com.github.maojx0630.common.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author MaoJiaXing
 */
public class DateUtils {

	public static String getTime(){
		return DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now());
	}

	public static String getDate(){
		return DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now());
	}

	public static String getDateTime(){
		return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	}

	public static String getDateTimeNumber(){
		return DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now());
	}
}
