package com.qbryx.tommystore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbryx.tommystore.dao.UserDao;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User authenticate(String username, String password) throws FailedLoginException {
		
		User user = userDao.findUser(username);
		
		if(user == null || !user.getPassword().equals(password)){
			throw new FailedLoginException();
		}
		
		return user;
	}

}
