package com.qbryx.tommystore.dao;

import com.qbryx.tommystore.domain.User;

public interface UserDao {

	User findUser(String email);
}
