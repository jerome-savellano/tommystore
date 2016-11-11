package com.qbryx.tommystore.service;

import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystrore.exception.DuplicateUserException;

public interface UserService {
	
	User authenticate(String username, String password) throws com.qbryx.tommystrore.exception.FailedLoginException;
	void createUser(User user) throws DuplicateUserException;
}
