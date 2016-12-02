package com.qbryx.tommystore.service;

import java.util.List;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystrore.exception.DuplicateUserException;

public interface UserService {
	
	List<User> findAll();
	
	List<User> findByType(UserType userType);
	
	List<User> findNewUsers();
	
	User findByEmail(String email);
	
	User authenticate(String username, String password) throws com.qbryx.tommystrore.exception.FailedLoginException;
	
	void save(User user) throws DuplicateUserException;
}
