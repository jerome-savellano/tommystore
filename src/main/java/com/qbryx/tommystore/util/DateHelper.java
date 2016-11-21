package com.qbryx.tommystore.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
	
	public static String now(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
}
