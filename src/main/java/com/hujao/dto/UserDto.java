package com.hujao.dto;

import lombok.Data;

@Data
public class UserDto {
	String id;
	String username;
	String userpassword;
	String useremail;
	String usermobile;
	String usernick;
	Integer userbirthyear;
	boolean mobileisvalid;
	boolean emailisvalid;
	boolean isdeleted;
}
