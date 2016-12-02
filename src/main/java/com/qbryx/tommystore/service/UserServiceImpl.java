package com.qbryx.tommystore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qbryx.tommystore.dao.UserDao;
import com.qbryx.tommystore.domain.User;
import com.qbryx.tommystore.enums.UserType;
import com.qbryx.tommystore.util.DateUtil;
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
	public void save(User newUser) throws DuplicateUserException {

		if (isUserExisting(newUser)) {
			throw new DuplicateUserException();
		}

		newUser.setDateCreated(DateUtil.now());
		userDao.save(newUser);
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

	@Override
	public List<User> findNewUsers() {
		return userDao.findNewUsers();
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}
}
