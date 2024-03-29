package com.learning.springboot.springboote2e.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learning.springboot.springboote2e.model.User;

@Component
public class UserRepository {
	
	private static List<User> users = new ArrayList<User>();
	
	private static int usersCount = 3;
	
	static {
		users.add(new User(1, "khana", new Date()));
		users.add(new User(2, "Pina", new Date()));
		users.add(new User(3, "Sona", new Date()));
	}
	
	public List<User> findAll () {
		return users;
	}
	
	public User save (User user) {
		if (user.getId() == null) 
			user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public User findOne (int id) {
		for (User user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	public User deleteById (int id) {
		// Alt + Shift + L -- to assign the result to a local variable
		Iterator<User> iterator = users.iterator();

		while(iterator.hasNext()) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
}
