package com.bubblebear.backendProject.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
//import com.bubblebear.backendProject.Repository.UserRepository;
import com.bubblebear.backendProject.entity.User;
import com.bubblebear.backendProject.service.UserService;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("api/user") //localhost:8080/api/user
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping  //localhost:8080/api/user
	public ResponseEntity<User> creatUser(@Validated @RequestBody User user) {
		User savedUsers = userService.createUser(user);
		return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")//localhost:8080/api/user/2
	public ResponseEntity<User> getUserById(@PathVariable long id) { 
	User user = userService.getUserById(id);
	return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	@GetMapping //localhost:8080/api/user
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> users = userService.getAllUsers(); 
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@PutMapping("{id}")//localhost:8080/api/user/6
	public ResponseEntity<User> updateUser(@RequestBody @Validated User user, @PathVariable long id) {
		User updatedUser = userService.updateUser(user, id);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}") //localhost:8080/api/user/9
	public ResponseEntity<String> deleteUsers(@PathVariable long id) {
		userService.deleteUser(id);
		return new ResponseEntity<>("User id " + id + " successfully deleted", HttpStatus.OK);
	}

}
