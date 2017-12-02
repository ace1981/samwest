package com.hujao.po;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
public class ArticleEntity {
	@Getter
	@Setter
	@Id
	String id;
}
