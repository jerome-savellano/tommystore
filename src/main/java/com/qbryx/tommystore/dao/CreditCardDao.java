package com.qbryx.tommystore.dao;

import java.util.List;

import com.qbryx.tommystore.domain.CreditCard;
import com.qbryx.tommystore.domain.User;

public interface CreditCardDao {
	
	List<CreditCard> findByUser(User user);
	
	CreditCard findById(long id);
	
	void save(CreditCard creditCard);
}
