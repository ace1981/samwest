package com.hujao.util;

import java.util.UUID;

public class GuidUtil {
	public static String getGuid(){
		return UUID.randomUUID().toString();
	}
}
