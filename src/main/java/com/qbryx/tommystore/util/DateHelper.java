package com.qbryx.tommystore.util;

import org.joda.time.DateTime;

public class DateHelper {
	
	public static void main(String[] args) {
		System.out.println(now());
	}
	
	public static DateTime now(){	
		return new DateTime("MM/dd/yyyy HH:mm:ss");
	}
}
