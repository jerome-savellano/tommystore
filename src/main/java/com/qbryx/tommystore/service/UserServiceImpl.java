package com.qbryx.tommystore.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.UserDao;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystrore.exception.DuplicateUserException;
import com.qbryx.tommystrore.exception.FailedLoginException;

@Service("userService")
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User authenticate(String email, String password) throws FailedLoginException {

		User user = userDao.findByEmail(email);

		if (user == null || !user.getPassword().equals(password)) {
			throw new FailedLoginException();
		}

		return user;
	}

	@Override
	@Transactional(readOnly = false)
	public void createUser(User newUser) throws DuplicateUserException {

		if (isUserExisting(newUser)) {
			throw new DuplicateUserException();
		}
		
		newUser.setDateCreated(new Date("yyyy-MM-dd"));
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

	private boolean isUserExisting(User user) {
		return userDao.findByEmail(user.getEmail()) != null;
	}
}
