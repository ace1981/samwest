package com.hujao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.ExportMetricReader;
import org.springframework.boot.actuate.autoconfigure.ExportMetricWriter;
import org.springframework.boot.actuate.metrics.reader.MetricReader;
import org.springframework.boot.actuate.metrics.reader.MetricRegistryMetricReader;
import org.springframework.boot.actuate.metrics.statsd.StatsdMetricWriter;
import org.springframework.boot.actuate.metrics.writer.MetricWriter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.codahale.metrics.MetricRegistry;

@Configuration
 
@EnableConfigurationProperties(StatsdProperties.class)
public class MetricsConfig {
	@Autowired
	private StatsdProperties statsdProperties;

	@Autowired
	private MetricRegistry metricRegistry;

	@Bean
	@ExportMetricReader
	public MetricReader metricReader() {
		return new MetricRegistryMetricReader(metricRegistry);
	}

	@Bean
	@ConditionalOnProperty(prefix = "statsd", name = { "host", "port" })
	@ExportMetricWriter
	public MetricWriter metricWriter() {
 
		 return new StatsdMetricWriter(statsdProperties.getHost(),statsdProperties.getPort());
	}
}
