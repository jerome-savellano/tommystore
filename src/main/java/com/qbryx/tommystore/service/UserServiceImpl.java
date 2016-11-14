package com.qbryx.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qbryx.tommystore.dao.UserDao;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystrore.exception.DuplicateUserException;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User authenticate(String email, String password) throws FailedLoginException {
		
		User user = userDao.findByEmail(email);
		
		if(user == null || !user.getPassword().equals(password)){
			throw new FailedLoginException();
		}
		
		return user;
	}

	@Override
	public void createUser(User newUser) throws DuplicateUserException {
		
		if(isUserExisting(newUser)){
			throw new DuplicateUserException();
		}
		
		userDao.createUser(newUser);
	}
		
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<User> findByType(UserType userType) {
		return userDao.findByType(userType);
	}
	
	private boolean isUserExisting(User user){
		return userDao.findByEmail(user.getEmail()) != null;
	}
}
