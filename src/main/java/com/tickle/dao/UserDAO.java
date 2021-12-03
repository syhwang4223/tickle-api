package com.tickle.dao;

import com.tickle.dto.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

	User login(User user);

}
