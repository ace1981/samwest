package com.hujao;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.codahale.metrics.MetricFilter;
import com.hujao.common.SpringUtil;

import metrics_influxdb.InfluxdbReporter;
import metrics_influxdb.api.protocols.InfluxdbProtocols;

@SpringBootApplication
@ComponentScan
public class SamwestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SamwestApplication.class, args);
		
	
	}
}
