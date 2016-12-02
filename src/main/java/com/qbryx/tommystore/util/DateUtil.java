package com.qbryx.tommystore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String now() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	public static String timeStamp() {
		return new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + System.nanoTime();
	}
}
