package com.github.maojx0630.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @author MaoJiaXing
 */
public class IdUtils {

	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public static String nanosecondStamp() {
		String a = System.nanoTime() + "";
		return DateUtils.getDateTimeNumber() + a.substring(a.length() - 7);
	}

	public static String randomString(int length) {
		StringBuilder sb = new StringBuilder(length);
		Random random = new Random();
		switch (random.nextInt(3)) {
			case 0:
				sb.append((char) ('A' + random.nextInt(26)));
				break;
			case 1:
				sb.append((char) ('a' + random.nextInt(26)));
				break;
			default:
				sb.append((char) ('0' + random.nextInt(10)));
				break;
		}
		return sb.toString();
	}

	public static String randomSpecialString(int length){
		StringBuilder sb=new StringBuilder(length);
		Random random=new Random();
		for (int i = 0; i < length; i++) {
			sb.append((char)('!'+random.nextInt(94)));
		}
		return sb.toString();
	}
}
