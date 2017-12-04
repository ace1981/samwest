package com.hujao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ListIndexBase;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.Data;

@Data
@Entity
@Table(name = "Article")
@Document(indexName="projectname",type="article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class ArticleEntity {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Column
	 
	String title;
}
