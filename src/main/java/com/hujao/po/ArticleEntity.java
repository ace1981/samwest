package com.hujao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.ListIndexBase;

import lombok.*;

 
@Entity
@Table(name = "Article")
public class ArticleEntity {
	@Getter@Setter
	@Id
	@GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	@Getter@Setter
	@Column
	String title;
}
