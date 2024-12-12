package com.springboot.myfirstwebapp.service;

import com.springboot.myfirstwebapp.entity.User;

public interface UserService {
	User saveUser(User user);

	boolean userExists(String username);
}
