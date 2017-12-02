package com.hujao.config;
 
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
	@EnableTransactionManagement
	@Import({ DruidAutoConfiguration.class })
	@EnableJpaRepositories(entityManagerFactoryRef="entityManagerFactory", transactionManagerRef="jpaTransactionManager",
	        basePackages = "com.hujao.dao.jpa")
	@MapperScan("com.hujao.dao.mybatis")//Mapper扫描
	public class DaoConfiguration {

	    @Bean
	    public JpaVendorAdapter jpaVendorAdapter(){
	        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
	        adapter.setDatabase(Database.MYSQL);
	        adapter.setShowSql(true);
	        adapter.setGenerateDdl(false);
	       
	        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
	        return adapter;
	    }

	    @Bean
	    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource){
	        LocalContainerEntityManagerFactoryBean emfb = new LocalContainerEntityManagerFactoryBean();         
	        emfb.setDataSource(dataSource);
	        emfb.setJpaVendorAdapter(jpaVendorAdapter());
	        emfb.setPackagesToScan("com.hujao.po");
	        Properties properties = new Properties();
	        properties.setProperty("hibernate.physical_naming_strategy", "org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy");
	        emfb.setJpaProperties(properties);
	        return emfb;
	    }

	    @Bean
	    public JpaTransactionManager jpaTransactionManager(DataSource dataSource) {
	        JpaTransactionManager jtm = new JpaTransactionManager();
	        jtm.setEntityManagerFactory(entityManagerFactory(dataSource).getObject());
	        return jtm;
	    }

	    @Bean
	    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
	        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource);
	        return sessionFactory.getObject();
	    }

	}
