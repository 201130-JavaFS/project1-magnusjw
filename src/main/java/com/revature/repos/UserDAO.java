package com.revature.repos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	public List<User> findAll();
	public User findByUsername(String username);

}
