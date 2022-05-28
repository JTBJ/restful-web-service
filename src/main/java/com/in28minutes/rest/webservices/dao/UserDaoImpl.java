package com.in28minutes.rest.webservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.in28minutes.rest.webservices.model.user.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static {
		users.add(new User(1, "James", new Date()));
		users.add(new User(2, "Ranga", new Date()));
		users.add(new User(3, "John", new Date()));
	}

	@Override
	public List<User> findAll() {
		return users;
	}

	@Override
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++usersCount);			
		}
		users.add(user);
		return user;
	}

	@Override
	public User findById(int id) {
		for(User user : users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User deleteById(int id) {
		User user = null;
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			user = (User)iterator.next();
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
