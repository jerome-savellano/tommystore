package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;

public interface UserDao {

	List<User> findAll();
	
	List<User> findByType(UserType userType);
	
	List<User> findNewUsers();
		
	User findByEmail(String email);
	
	void createUser(User user);
}
