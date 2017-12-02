package com.hujao.dto;

import lombok.Data;

@Data
public class UserDto {
	String id;
	String username;
	String userpassword;
	String useremail;
	String usernick;
	Integer userbirthyear;
}
