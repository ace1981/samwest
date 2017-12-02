package com.hujao.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hujao.po.UserEntity;

public interface UserDao extends JpaRepository<UserEntity, String> {
	 
}



 
