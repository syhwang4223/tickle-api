package com.tickle.service;

import com.tickle.dto.User;

public interface UserService {

	User login(User user);
	
	String getUserName(Integer uID);
	
}
