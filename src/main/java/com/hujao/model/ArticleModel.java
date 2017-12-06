package com.hujao.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import lombok.*;
@Document(indexName="samwest",type="article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class ArticleModel {
	@Getter@Setter	
	@Id
	String id;
	@Getter@Setter
	String title;
}
