package com.qbryx.tommystore.service;

import com.qbryx.tommystore.domain.User;

public interface UserService {
	
	User authenticate(String username, String password) throws com.qbryx.tommystrore.exception.FailedLoginException;
}
