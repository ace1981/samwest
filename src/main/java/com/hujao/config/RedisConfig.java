package com.hujao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class RedisConfig {
	@Value("${spring.redis.host}")
	String host;
	@Value("${spring.redis.password}")
	String password;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
