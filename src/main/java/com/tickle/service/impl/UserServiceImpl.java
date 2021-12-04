package com.tickle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tickle.dao.UserDAO;
import com.tickle.dto.User;
import com.tickle.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAO dao;
	
	@Override
	public User login(User user) {
		return dao.login(user);
	}
	@Override
	public String getUserName(Integer uID) {
		return dao.getUserName(uID);
	}
	

}