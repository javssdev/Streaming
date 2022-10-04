package com.tmx.dashboard.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utils {
	
	public static void print(String txt) {
		if (Cs.DEVELOPMENT) { log.info(txt); }
	}
}