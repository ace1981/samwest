package com.hujao.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
@Data
@Entity
@Table(name = "userbasicinfo")
public class UserEntity {
	 
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	String id;
	 
	@Column
	String username;
	@Column
	String userpassword;
	@Column
	String useremail;
	@Column
	String usernick;
	@Column
	Integer userbirthyear;
}
