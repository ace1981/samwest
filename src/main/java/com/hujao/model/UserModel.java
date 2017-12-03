package com.hujao.model;

import javax.persistence.Column;

import lombok.Data;
@Data
public class UserModel {
	String id;
	String username;
	String userpassword;
	String useremail;
	String usermobile;
	String usernick;
	Integer userbirthyear;
	boolean mobileisvalid;
	boolean emailisvalid;
}
