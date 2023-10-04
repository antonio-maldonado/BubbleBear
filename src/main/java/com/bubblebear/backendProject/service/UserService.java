package com.bubblebear.backendProject.service;

import java.util.List;

import com.bubblebear.backendProject.entity.User;
import com.bubblebear.backendProject.entity.limits.UserFieldLimits;

public interface UserService extends UserFieldLimits{

	User createUser(User user); 

	User getUserById(long id);

	List<User> getAllUsers();

	User updateUser(User user, long id);

	User getUserByEmail(String email);

	void deleteUser(Long id);

}
