package com.myRestfulwebService.example.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.myRestfulwebService.example.model.UserObject;

@Component
public class UserDaoService {
	private static List<UserObject> users = new ArrayList<>();
	private static int userCount = 5;

	static {
		users.add(new UserObject(1, "Dante", new Date()));
		users.add(new UserObject(2, "Vergil", new Date()));
		users.add(new UserObject(3, "Trish", new Date()));
		users.add(new UserObject(4, "Lady", new Date()));
		users.add(new UserObject(5, "Sparda", new Date()));
	}

	public List<UserObject> findAllUsers() {
		return users;
	}

	public UserObject saveUser(UserObject user) {
		if (user.getId() == null)
			user.setId(++userCount);
		users.add(user);
		return user;
	}

	public UserObject findOneUser(int id) {
		for (UserObject user : users) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	public UserObject deleteOneUser(int id) {
		Iterator<UserObject> userIterator = users.iterator();
		while (userIterator.hasNext()) {
			UserObject user = userIterator.next();
			if (user.getId() == id) {
				userIterator.remove();
				return user;
			}
		}
		return null;
	}

}
