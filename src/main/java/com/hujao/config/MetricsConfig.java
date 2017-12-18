package com.hujao.config;

import java.util.concurrent.TimeUnit;

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

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricFilter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.ScheduledReporter;

import metrics_influxdb.InfluxdbReporter;
import metrics_influxdb.api.protocols.InfluxdbProtocols;

@Configuration
 
@EnableConfigurationProperties(StatsdProperties.class)
public class MetricsConfig {
	@Autowired
	private StatsdProperties statsdProperties;

	@Autowired
	private MetricRegistry metricRegistry;
	
	
	@Bean(name = "influxdbReporter")
	public ScheduledReporter influxdbReporter(MetricRegistry metrics) throws Exception {
		
		ScheduledReporter rep= InfluxdbReporter.forRegistry(metrics)
	            //.protocol(InfluxdbProtocols.http("host_ip_address", port, "username", "password", "database"))
	            .protocol(InfluxdbProtocols.http("118.190.77.10", 8086, "ace", "ace.123", "sam"))
	            .convertRatesTo(TimeUnit.SECONDS)
	            .convertDurationsTo(TimeUnit.MILLISECONDS)
	            .filter(MetricFilter.ALL)
	            .skipIdleMetrics(false)
	            .build();
		rep.start(5, TimeUnit.SECONDS);
		return rep;
	}

	@Bean
	@ExportMetricReader
	public MetricReader metricReader() {
		metricRegistry.register("com.hujao.freemem", new Gauge<Long>(){  
            public Long getValue() {  
                //这里是获取当前JVM可用内存  
                return Runtime.getRuntime().freeMemory();  
            }  
        });
		metricRegistry.register("com.hujao.totalmem", new Gauge<Long>(){  
            public Long getValue() {  
                //这里是获取当前JVM总内存  
                return Runtime.getRuntime().totalMemory();                
            }  
        });
		metricRegistry.register("com.hujao.maxmem", new Gauge<Long>(){  
            public Long getValue() {  
                //这里是获取当前JVM最大内存  
                return Runtime.getRuntime().maxMemory();                
            }  
        });
		
		return new MetricRegistryMetricReader(metricRegistry);
	}

//	@Bean
//	@ConditionalOnProperty(prefix = "statsd", name = { "host", "port" })
//	@ExportMetricWriter
//	public MetricWriter metricWriter() {
// 
//		 return new StatsdMetricWriter(statsdProperties.getHost(),statsdProperties.getPort());
//	}
}
