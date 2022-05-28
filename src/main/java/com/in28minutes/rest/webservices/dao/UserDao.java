package com.in28minutes.rest.webservices.dao;

import java.util.List;

import com.in28minutes.rest.webservices.model.user.User;

public interface UserDao {

	List<User> findAll();
	User save(User user);
	User findById(int id);
	User deleteById(int id);
}
