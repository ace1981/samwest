package com.hujao.common;

import java.sql.Timestamp;

public class TimeUtil {
	public static Timestamp getNowTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
}
