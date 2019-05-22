package com.github.maojx0630.common.utils;

import java.util.Base64;

/**
 * @author: MaoJiaXing
 * @date: 2019-05-22 15:45
 * @description:
 */
public class Base64Utils {


	public static String encode(byte[] bytes){
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static byte[] decode(String string){
		return Base64.getDecoder().decode(string);
	}
}
